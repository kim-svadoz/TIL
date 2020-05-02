package location;

public class locationVO {
	private String loc_code;
	private String loc_name;
	private double loc_lat;
	private double loc_lon;
	
	public locationVO() {
	
	}

	public locationVO(String loc_code, String loc_name, double loc_lat, double loc_lon) {
		super();
		this.loc_code = loc_code;
		this.loc_name = loc_name;
		this.loc_lat = loc_lat;
		this.loc_lon = loc_lon;
	}

	@Override
	public String toString() {
		return "locationVO [loc_code=" + loc_code + ", loc_name=" + loc_name + ", loc_lat=" + loc_lat + ", loc_lon="
				+ loc_lon + "]";
	}

	public String getLoc_code() {
		return loc_code;
	}

	public void setLoc_code(String loc_code) {
		this.loc_code = loc_code;
	}

	public String getLoc_name() {
		return loc_name;
	}

	public void setLoc_name(String loc_name) {
		this.loc_name = loc_name;
	}

	public double getLoc_lat() {
		return loc_lat;
	}

	public void setLoc_lat(double loc_lat) {
		this.loc_lat = loc_lat;
	}

	public double getLoc_lon() {
		return loc_lon;
	}

	public void setLoc_lon(double loc_lon) {
		this.loc_lon = loc_lon;
	}
	
}
