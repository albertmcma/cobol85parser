/*
 * Copyright (C) 2017, Ulrich Wolffgang <ulrich.wolffgang@proleap.io>
 * All rights reserved.
 *
 * This software may be modified and distributed under the terms
 * of the MIT license. See the LICENSE file for details.
 */

package io.proleap.cobol.preprocessor.sub.copybook.impl;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.commons.io.FileUtils;

import io.proleap.cobol.Cobol85PreprocessorParser.FilenameContext;
import io.proleap.cobol.asg.params.CobolParserParams;
import io.proleap.cobol.preprocessor.sub.copybook.FilenameCopyBookFinder;

public class FilenameCopyBookFinderImpl implements FilenameCopyBookFinder {

	@Override
	public File findCopyBook(final CobolParserParams params, final FilenameContext ctx) {
		if (params.getCopyBookFiles() != null) {
			for (final File copyBookFile : params.getCopyBookFiles()) {
				if (isMatchingCopyBook(copyBookFile, null, ctx)) {
					return copyBookFile;
				}
			}
		}

		if (params.getCopyBookDirectories() != null) {
			for (final File copyBookDirectory : params.getCopyBookDirectories()) {
				final File validCopyBook = findCopyBookInDirectory(copyBookDirectory, ctx);

				if (validCopyBook != null) {
					return validCopyBook;
				}
			}
		}

		return null;
	}

	protected File findCopyBookInDirectory(final File copyBooksDirectory, final FilenameContext ctx) {
		for (final File copyBookCandidate : FileUtils.listFiles(copyBooksDirectory, null, true)) {
			if (isMatchingCopyBook(copyBookCandidate, copyBooksDirectory, ctx)) {
				return copyBookCandidate;
			}
		}

		return null;
	}

	protected boolean isMatchingCopyBook(final File copyBookCandidate, final File cobolCopyDir,
			final FilenameContext ctx) {
		final String copyBookIdentifier = ctx.getText();
		final boolean result;

		if (cobolCopyDir == null) {
			result = isMatchingCopyBookRelative(copyBookCandidate, copyBookIdentifier);
		} else {
			result = isMatchingCopyBookAbsolute(copyBookCandidate, cobolCopyDir, copyBookIdentifier);
		}

		return result;
	}

	protected boolean isMatchingCopyBookAbsolute(final File copyBookCandidate, final File cobolCopyDir,
			final String copyBookIdentifier) {
		final Path copyBookCandidateAbsolutePath = Paths.get(copyBookCandidate.getAbsolutePath()).normalize();
		final Path copyBookIdentifierAbsolutePath = Paths.get(cobolCopyDir.getAbsolutePath(), copyBookIdentifier)
				.normalize();
		final boolean result = copyBookIdentifierAbsolutePath.toString()
				.equalsIgnoreCase(copyBookCandidateAbsolutePath.toString());
		return result;
	}

	protected boolean isMatchingCopyBookRelative(final File copyBookCandidate, final String copyBookIdentifier) {
		final Path copyBookCandidateAbsolutePath = Paths.get(copyBookCandidate.getAbsolutePath()).normalize();
		final Path copyBookIdentifierRelativePath = Paths.get("/" + copyBookIdentifier).normalize();

		final boolean result = copyBookCandidateAbsolutePath.toString().toLowerCase()
				.endsWith(copyBookIdentifierRelativePath.toString().toLowerCase());
		return result;
	}
}
