package ananas.lib.impl.io.vfs;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import ananas.lib.io.vfs.VFile;
import ananas.lib.io.vfs.VFileSystem;
import ananas.lib.io.vfs.VFileSystemConfiguration;
import ananas.lib.io.vfs.VFileSystemFactory;
import ananas.lib.io.vfs.VPathAbsolute;
import ananas.lib.io.vfs.VPathRelative;

public class VFileSystemImpl implements VFileSystem {

	private VFileSystemConfiguration mConfig;
	private VFileSystemFactory mFactory;

	public VFileSystemImpl(VFileSystemFactory factory,
			VFileSystemConfiguration config) {
		this.mConfig = config;
		this.mFactory = factory;
	}

	@Override
	public VFileSystemFactory getFactory() {
		return this.mFactory;
	}

	@Override
	public VFileSystemConfiguration getConfiguration() {
		return this.mConfig;
	}

	@Override
	public VFile newFile(VFile dir, String string) {
		VFileImpl dir1 = VFileImpl.getInst(this, dir);
		File dir2 = dir1.toFile();
		File file = new File(dir2, string);
		return new VFileImpl(this, file);
	}

	@Override
	public VFile newFile(String path) {
		File file = new File(path);
		return new VFileImpl(this, file);
	}

	@Override
	public VFile newFile(String dir, String string) {
		File file = new File(dir, string);
		return new VFileImpl(this, file);
	}

	@Override
	public VFile newFile(URI uri) {
		File file = new File(uri);
		return new VFileImpl(this, file);
	}

	@Override
	public String pathSeparator() {
		return File.pathSeparator;
	}

	@Override
	public String separator() {
		return File.separator;
	}

	@Override
	public VPathAbsolute newAbsolutePath(String string) {
		return VPathImpl.newAbsolutePath(this, string);
	}

	@Override
	public VPathRelative newRelativePath(String string) {
		return VPathImpl.newRelativePath(this, string);
	}

	@Override
	public char pathSeparatorChar() {
		return File.pathSeparatorChar;
	}

	@Override
	public char separatorChar() {
		return File.separatorChar;
	}

	@Override
	public VFile createTempFile(String prefix, String suffix)
			throws IOException {
		File file = File.createTempFile(prefix, suffix);
		return new VFileImpl(this, file);
	}

	@Override
	public VFile createTempFile(String prefix, String suffix, VFile directory)
			throws IOException {
		VFileImpl dir2 = VFileImpl.getInst(this, directory);
		File file = File.createTempFile(prefix, suffix, dir2.toFile());
		return new VFileImpl(this, file);
	}

	@Override
	public VFile[] listRoots() {
		File[] array = File.listRoots();
		return VFileImpl.arrayFrom(this, array);
	}

}
