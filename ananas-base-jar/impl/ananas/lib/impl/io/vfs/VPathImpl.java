package ananas.lib.impl.io.vfs;

import java.util.ArrayList;
import java.util.List;

import ananas.lib.io.vfs.VFileSystem;
import ananas.lib.io.vfs.VPath;
import ananas.lib.io.vfs.VPathAbsolute;
import ananas.lib.io.vfs.VPathRelative;

class VPathImpl {

	public static VPathAbsolute newAbsolutePath(VFileSystem vfs, String string) {
		String sp = vfs.separator();
		String[] array = stringToArray(string);
		String s2 = arrayToString(sp, array, sp);
		return new VPathAbsoluteImpl(vfs, s2);
	}

	public static VPathRelative newRelativePath(VFileSystem vfs, String string) {
		String sp = vfs.separator();
		String[] array = stringToArray(string);
		String s2 = arrayToString(null, array, sp);
		return new VPathRelativeImpl(vfs, s2);
	}

	static class BaseVPath implements VPath {

		private final VFileSystem _vfs;
		private final String _str;

		BaseVPath(VFileSystem vfs, String str) {
			this._vfs = vfs;
			this._str = str;
		}

		@Override
		public VFileSystem getVFS() {
			return this._vfs;
		}

		public String toString() {
			return this._str;
		}
	}

	static class VPathRelativeImpl extends BaseVPath implements VPathRelative {

		VPathRelativeImpl(VFileSystem vfs, String str) {
			super(vfs, str);
		}
	}

	static class VPathAbsoluteImpl extends BaseVPath implements VPathAbsolute {

		VPathAbsoluteImpl(VFileSystem vfs, String str) {
			super(vfs, str);
		}

		@Override
		public boolean isSubOf(VPathAbsolute base) {
			String f = this.toString();
			String b = base.toString();
			return (f.startsWith(b));
		}

		@Override
		public VPathRelative getOffset(VPathAbsolute base) {
			String f = this.toString();
			String b = base.toString();
			if (!f.startsWith(b)) {
				return null;
			}
			String string = f.substring(b.length());
			return VPathImpl.newRelativePath(base.getVFS(), string);
		}

	}

	static void __add_component(List<String> list, String string) {
		if (string.length() > 0) {
			string = string.trim();
		} else {
			return;
		}
		if (string.length() == 0)
			return;
		if (string.equals("."))
			return;
		if (string.equals("..")) {
			list.remove(list.size() - 1);
			return;
		}
		list.add(string);
	}

	static String arrayToString(String begin, String[] array, String sp) {

		StringBuilder sb = new StringBuilder();
		if (begin != null) {
			sb.append(begin);
		}
		final int len = array.length;
		for (int i = 0; i < len; i++) {
			final String node = array[i];
			if (i > 0) {
				sb.append(sp);
			}
			sb.append(node);
		}
		return sb.toString();
	}

	static String[] stringToArray(String path) {

		StringBuilder sb = new StringBuilder();
		char[] chs = path.toCharArray();
		List<String> list = new ArrayList<String>();

		for (char ch : chs) {
			switch (ch) {
			case '\\':
			case '/': {
				__add_component(list, sb.toString());
				sb.setLength(0);
				break;
			}
			default:
				sb.append(ch);
				break;
			}
		}
		__add_component(list, sb.toString());
		String[] array = list.toArray(new String[list.size()]);
		return array;
	}

}
