/*
 * Copyright 2010 Srikanth Reddy Lingala  
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); 
 * you may not use this file except in compliance with the License. 
 * You may obtain a copy of the License at 
 * 
 * http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, 
 * software distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */

package com.org.testagent.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

public class FilesZip {
	
	public void CreateEncriptedZip() {

		try {
			Files.deleteIfExists(Paths.get("c:\\temp\\CreateZipWithOutputStreamsStandardEnc.zip"));
			ZipFile zipfile = new ZipFile(new File("c:\\temp\\CreateZipWithOutputStreamsStandardEnc.zip"));
			ZipParameters parameters = new ZipParameters();
			parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
			parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
			parameters.setIncludeRootFolder(false);
			parameters.setEncryptFiles(true);
			parameters.setEncryptionMethod(Zip4jConstants.ENC_METHOD_AES);
			parameters.setAesKeyStrength(Zip4jConstants.AES_STRENGTH_256);
			parameters.setPassword("YourPassword");
			zipfile.addFolder("c:/temp/temp/", parameters);
		} catch (ZipException | IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void ExtractAllFiles() {
		try {
			ZipFile zipFile = new ZipFile("c:\\temp\\CreateZipWithOutputStreamsStandardEnc.zip");
			zipFile.setPassword("YourPassword");
			zipFile.extractAll("c:\\temp\\");
		} catch (ZipException e) {
			e.printStackTrace();
		}
	}
}
