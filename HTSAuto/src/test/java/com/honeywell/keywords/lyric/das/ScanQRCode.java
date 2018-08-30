package com.honeywell.keywords.lyric.das;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.honeywell.commons.coreframework.AfterKeyword;
import com.honeywell.commons.coreframework.BeforeKeyword;
import com.honeywell.commons.coreframework.Keyword;
import com.honeywell.commons.coreframework.KeywordStep;
import com.honeywell.commons.coreframework.TestCaseInputs;
import com.honeywell.commons.coreframework.TestCases;
import com.honeywell.commons.mobile.MobileUtils;
import com.honeywell.commons.report.FailType;
import com.honeywell.lyric.utils.JasperUtils;

public class ScanQRCode extends Keyword {

	private TestCases testCase;

	public boolean flag = true;

	public ScanQRCode(TestCases testCase, TestCaseInputs inputs) {
		this.testCase = testCase;
	}

	@Override
	@BeforeKeyword
	public boolean preCondition() {

		return flag;
	}

	@Override
	@KeywordStep(gherkins = "^user scans the QR code by showing it to the base station camera$")
	public boolean keywordSteps() {
		JFrame frame = new JFrame();
		try {
			String screenShotPath = JasperUtils.takeScreenShot(
					System.getProperty("user.dir") + File.separator + "QRCodes", testCase.getMobileDriver());
			screenShotPath = System.getProperty("user.dir") + File.separator + "QRCodes" + File.separator
					+ screenShotPath;

			BufferedImage picture = ImageIO.read(new File(screenShotPath));
			ImageIcon imageIcon = new ImageIcon(
					new ImageIcon(picture).getImage().getScaledInstance(500, 700, Image.SCALE_DEFAULT));
			JLabel label = new JLabel(imageIcon);
			frame.add(label);
			frame.pack();
			frame.setAlwaysOnTop(true);
			frame.setVisible(true);
			
			String locatorType, locatorValue;
			if (testCase.getPlatform().toUpperCase().contains("ANDROID")) {
				locatorType = "xpath";
				locatorValue = "//android.widget.TextView[@text='Loading wifi list']";
			} else {
				locatorType = "name";
				locatorValue = "Loading wifi list";
			}
			if (MobileUtils.isMobElementExists(locatorType, locatorValue, testCase, 30)) {
				Keyword.ReportStep_Pass(testCase, "QR code successfully scanned");
			} else {
				flag = false;
				Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "QR code not scanned");
			}
			frame.setVisible(false);
			frame.dispose();
		} catch (IOException e) {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Error Occured: " + e.getMessage());
			frame.setVisible(false);
			frame.dispose();
		} catch (Exception e) {
			flag = false;
			Keyword.ReportStep_Fail(testCase, FailType.FUNCTIONAL_FAILURE, "Error Occured: " + e.getMessage());
			frame.setVisible(false);
			frame.dispose();
		}
		return flag;
	}

	@Override
	@AfterKeyword
	public boolean postCondition() {
		return flag;
	}

}
