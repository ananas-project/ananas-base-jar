package ananas.lib.impl.io.vfs;

import java.io.File;
import java.io.FilenameFilter;

import ananas.lib.io.vfs.VFile;
import ananas.lib.io.vfs.VFileSystem;
import ananas.lib.io.vfs.VFilenameFilter;

public class VFilenameFilterAdapter implements FilenameFilter {

	private final VFileSystem _vfs;
	private final VFilenameFilter _filter;

	public VFilenameFilterAdapter(VFileSystem vfs, VFilenameFilter filter) {
		this._vfs = vfs;
		this._filter = filter;
	}

	@Override
	public boolean accept(File file, String name) {
		VFile vf = new VFileImpl(_vfs, file);
		return this._filter.accept(vf, name);
	}

}
