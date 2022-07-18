package Main;

public class StatsReader {
	private int numero_familier;
	private double exp;
	private int version;
	private int pv;
	private int pm;
	private final int attaque_plus[] = {10, 12, 14, 17, 21, 26, 31, 38, 46, 56};
	private final int attaque_moins[] = {2, 4, 6, 9, 13, 18, 23, 30, 38, 48};
	private final int attaque_normal[] = {6, 8, 10, 13, 17, 22, 27, 34, 42, 52};
	private final int pm_plus[] = {20, 24, 30, 37, 45, 56, 69, 85, 104, 129};
	private final int pm_moins[] = {2, 6, 12, 19, 27, 38, 51, 67, 86, 111};
	private final int pm_normal[] = {10, 14, 20, 27, 35, 46, 59, 75, 94, 119};
	private final int pv_plus[] = {50, 61, 75, 92, 113, 139, 170, 209, 256, 315};
	private final int pv_moins[] = {20, 31, 45, 62, 83, 109, 140, 179, 226, 285};
	private final int pv_normal[] = {30, 41, 55, 72, 93, 119, 150, 189, 236, 295};
	private final String[] list = new String[] {
		"-", "-", "-",
		"-", "", "",
		"", "+", "+",
		"+", "-", "",
		"", "+", "-",
		"-", "+", "-",
		"-", "+", "",
		"+", "", "",
		"+", "+", "-",
		"", "+", "",
		"", "", "",
		"+", "", "",
		"", "+", "",
		"+", "", "-",
		"-", "", "",
		"+", "-", "-",
		"", "", "+",
		"+", "", "-",
		"-", "+", "",
		"+", "-", ""
	};
	
	public StatsReader(int numero_familier, int exp, int version, int pv, int pm) {
		this.numero_familier = numero_familier;
		this.exp = (double) exp;
		this.version = version;
		this.pv = pv;
		this.pm = pm;
	}

	public int get_niveau() {
		double c = 1;
		double b = Math.log(9) / Math.log(4269./20.);
		double a = 1. / Math.pow(20, b);
		return Math.max(Math.min((int) (a * Math.pow(exp, b) + c), 10), 1);
	}
	
	public int get_PV_MAX() {
		int niveau = get_niveau();
		return list[3 * (numero_familier - 1) + 1].equals("") ? pv_normal[niveau - 1] : (list[3 * (numero_familier - 1) + 1].equals("-") ? pv_moins[niveau - 1]: pv_plus[niveau - 1]);
	}
	
	public int get_PM_MAX() {
		int niveau = get_niveau();
		return list[3 * (numero_familier - 1) + 2].equals("") ? pm_normal[niveau - 1] : (list[3 * (numero_familier - 1) + 2].equals("-") ? pm_moins[niveau - 1]: pm_plus[niveau - 1]);
	}
	
	public int get_attaque() {
		int niveau = get_niveau();
		return list[3 * (numero_familier - 1)].equals("") ? attaque_normal[niveau - 1] : (list[3 * (numero_familier - 1)].equals("-") ? attaque_moins[niveau - 1]: attaque_plus[niveau - 1]);
	}
	
	public String getstats() {
		if (version == 0) {
			return "-";
		}
		int niveau = get_niveau();
		int PV_MAX = get_PV_MAX();
		int PM_MAX = get_PM_MAX();
		return "niv. " + String.valueOf(niveau) + "\n" + String.valueOf(this.pv) + "/" + String.valueOf(PV_MAX) + " PV\n" + String.valueOf(this.pm) +"/" + String.valueOf(PM_MAX) + " PM";
	}
}
