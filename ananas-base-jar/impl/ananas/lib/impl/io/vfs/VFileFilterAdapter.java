package ananas.lib.impl.io.vfs;

import java.io.File;
import java.io.FileFilter;

import ananas.lib.io.vfs.VFile;
import ananas.lib.io.vfs.VFileFilter;
import ananas.lib.io.vfs.VFileSystem;

public class VFileFilterAdapter implements FileFilter {

	private final VFileFilter _filter;
	private final VFileSystem _vfs;

	public VFileFilterAdapter(VFileSystem vfs, VFileFilter filter) {
		this._vfs = vfs;
		this._filter = filter;
	}

	@Override
	public boolean accept(File file) {
		VFile vf = new VFileImpl(this._vfs, file);
		return this._filter.accept(vf);
	}

}
