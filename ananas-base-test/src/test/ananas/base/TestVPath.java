package test.ananas.base;

import java.util.ArrayList;
import java.util.List;

import ananas.lib.io.vfs.VFS;
import ananas.lib.io.vfs.VFileSystem;
import ananas.lib.io.vfs.VPath;
import ananas.lib.io.vfs.VPathRelative;

public class TestVPath {

	public static void main(String[] arg) {

		TestVPath test = new TestVPath();
		test.run();

	}

	final VFileSystem vfs = VFS.getDefaultFactory().defaultFileSystem();

	private void run() {

		List<String> list = new ArrayList<String>();
		list.add("");
		list.add("/");
		list.add("\\");
		list.add("c:\\abc.com");
		list.add(" abc\\\\ def / g ");
		list.add("ab/c\\\\ def / g/");
		list.add("/a/b/c\\\\ def / g ");
		list.add("/a/b/c\\\\ def / g/");
		list.add("/a/b/c\\.\\ def / g/");
		list.add("/a/b/c\\..\\ def / g/");

		String bar = "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~";
		System.out.println(bar);

		for (String item : list)
			this.__do_abs(item);

		System.out.println(bar);

		for (String item : list)
			this.__do_rel(item);

	}

	private void __do_rel(String str) {
		VPathRelative path = vfs.newRelativePath(str);
		this.__do_path(str, path);
	}

	private void __do_path(String raw, VPath path) {
		String cls = path.getClass().getSimpleName();
		System.out.println(cls + "    " + path + " <<== " + raw);

	}

	private void __do_abs(String str) {
		VPath path = vfs.newAbsolutePath(str);
		this.__do_path(str, path);
	}

}
