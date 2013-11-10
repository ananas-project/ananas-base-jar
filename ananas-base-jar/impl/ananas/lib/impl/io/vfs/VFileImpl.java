package ananas.lib.impl.io.vfs;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.URI;

import ananas.lib.io.vfs.VFile;
import ananas.lib.io.vfs.VFileFilter;
import ananas.lib.io.vfs.VFileSystem;
import ananas.lib.io.vfs.VFilenameFilter;

public class VFileImpl implements VFile {

	private final File mFile;
	private final VFileSystem mVFS;

	public VFileImpl(VFileSystem vfs, File file) {
		this.mFile = file;
		this.mVFS = vfs;
	}

	@Override
	public VFileSystem getVFS() {
		return this.mVFS;
	}

	@Override
	public boolean exists() {
		return this.mFile.exists();
	}

	@Override
	public boolean mkdirs() {
		return this.mFile.mkdirs();
	}

	@Override
	public boolean isDirectory() {
		return this.mFile.isDirectory();
	}

	@Override
	public boolean createNewFile() throws IOException {
		return this.mFile.createNewFile();
	}

	@Override
	public VFile getParentFile() {
		File pf = this.mFile.getParentFile();
		if (pf == null) {
			return null;
		}
		return new VFileImpl(this.mVFS, pf);
	}

	@Override
	public boolean isFile() {
		return this.mFile.isFile();
	}

	@Override
	public URI toURI() {
		return this.mFile.toURI();
	}

	@Override
	public String getName() {
		return this.mFile.getName();
	}

	public String toString() {
		return this.mFile.toString();
	}

	@Override
	public boolean mkdir() {
		return this.mFile.mkdir();
	}

	@Override
	public String getAbsolutePath() {
		return this.mFile.getAbsolutePath();
	}

	private File __cast(VFile file) {
		if (file instanceof VFileImpl) {
			VFileImpl impl = (VFileImpl) file;
			return impl.mFile;
		} else {
			return new File(file.toURI());
		}
	}

	@Override
	public boolean renameTo(VFile dest) {
		File to = __cast(dest);
		return this.mFile.renameTo(to);
	}

	@Override
	public boolean delete() {
		return mFile.delete();
	}

	@Override
	public long lastModified() {
		return mFile.lastModified();
	}

	@Override
	public long length() {
		return mFile.length();
	}

	public static VFileImpl getInst(VFileSystem vfs, VFile dir) {
		if (dir instanceof VFileImpl) {
			VFileImpl impl = (VFileImpl) dir;
			return impl;
		} else {
			URI uri = dir.toURI();
			File file = new File(uri);
			return new VFileImpl(vfs, file);
		}
	}

	public File toFile() {
		return this.mFile;
	}

	public static VFile[] arrayFrom(VFileSystem vfs, File[] array) {
		VFile[] ret = new VFile[array.length];
		for (int i = array.length - 1; i >= 0; i--) {
			File file = array[i];
			VFile vfile = new VFileImpl(vfs, file);
			ret[i] = vfile;
		}
		return ret;
	}

	@Override
	public boolean canExecute() {
		return mFile.canExecute();
	}

	@Override
	public boolean canRead() {
		return mFile.canRead();
	}

	@Override
	public boolean canWrite() {
		return mFile.canWrite();
	}

	@Override
	public int compareTo(VFile pathname) {
		File to = __cast(pathname);
		return mFile.compareTo(to);
	}

	@Override
	public void deleteOnExit() {
		mFile.deleteOnExit();
	}

	@Override
	public VFile getAbsoluteFile() {
		return new VFileImpl(this.mVFS, mFile.getAbsoluteFile());
	}

	@Override
	public VFile getCanonicalFile() throws IOException {
		return new VFileImpl(mVFS, mFile.getCanonicalFile());
	}

	@Override
	public String getCanonicalPath() throws IOException {
		return mFile.getCanonicalPath();
	}

	@Override
	public long getFreeSpace() {
		return mFile.getFreeSpace();
	}

	@Override
	public String getParent() {
		return mFile.getParent();
	}

	@Override
	public String getPath() {
		return mFile.getPath();
	}

	@Override
	public long getTotalSpace() {
		return mFile.getTotalSpace();
	}

	@Override
	public long getUsableSpace() {
		return mFile.getUsableSpace();
	}

	@Override
	public boolean isAbsolute() {
		return mFile.isAbsolute();
	}

	@Override
	public boolean isHidden() {
		return mFile.isHidden();
	}

	@Override
	public String[] list() {
		return mFile.list();
	}

	@Override
	public String[] list(VFilenameFilter filter) {
		return mFile.list(new VFilenameFilterAdapter(mVFS, filter));
	}

	@Override
	public VFile[] listFiles() {
		return VFileImpl.arrayFrom(mVFS, mFile.listFiles());
	}

	@Override
	public VFile[] listFiles(VFileFilter filter) {
		FileFilter ada = new VFileFilterAdapter(mVFS, filter);
		File[] array = mFile.listFiles(ada);
		return VFileImpl.arrayFrom(mVFS, array);
	}

	@Override
	public VFile[] listFiles(VFilenameFilter filter) {
		FilenameFilter ada = new VFilenameFilterAdapter(mVFS, filter);
		File[] array = mFile.listFiles(ada);
		return VFileImpl.arrayFrom(mVFS, array);
	}

	@Override
	public boolean setExecutable(boolean executable) {
		return mFile.setExecutable(executable);
	}

	@Override
	public boolean setExecutable(boolean executable, boolean ownerOnly) {
		return mFile.setExecutable(executable, ownerOnly);
	}

	@Override
	public boolean setLastModified(long time) {
		return mFile.setLastModified(time);
	}

	@Override
	public boolean setReadable(boolean readable) {
		return mFile.setReadable(readable);
	}

	@Override
	public boolean setReadable(boolean readable, boolean ownerOnly) {
		return mFile.setReadable(readable, ownerOnly);
	}

	@Override
	public boolean setReadOnly() {
		return mFile.setReadOnly();
	}

	@Override
	public boolean setWritable(boolean writable) {
		return mFile.setWritable(writable);
	}

	@Override
	public boolean setWritable(boolean writable, boolean ownerOnly) {
		return mFile.setWritable(writable, ownerOnly);
	}
}
