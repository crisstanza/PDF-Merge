package io.github.crisstanza.pdfmerge;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.pdfbox.multipdf.PDFMergerUtility;

public final class Library
{
	private final String in = "/tmp/pdfs/";
	private final String out = "out.pdf";

	public boolean start() throws Exception
	{
		final File inFolder = new File(in);
		final File[] inFiles = inFolder.listFiles(new FilenameFilter()
		{
			public boolean accept(File dir, String name)
			{
				return name.toLowerCase().endsWith(".pdf");
			}
		});

		if (inFiles == null || inFiles.length == 0)
		{
			return false;
		}

		final PDFMergerUtility pdfMerger = new PDFMergerUtility();
		for (int i = 0; i < inFiles.length; i++)
		{
			final File inFile = inFiles[i];
			pdfMerger.addSource(inFile);
		}
		pdfMerger.setDestinationFileName(in + out);
		pdfMerger.mergeDocuments(null);

		return true;
	}

}
