package newsagents;

public class DeliveryArea {
	
	private int deliveryAreaId;
	private String name;
	private String region;
	private int driverId;
	
	public DeliveryArea(String name, String region, int driverId) {
		this.name = name;
		this.region = region;
		this.driverId = driverId;
	}
	
	public int getDeliveryAreaId() {
		return deliveryAreaId;
	}

	public void setDeliveryAreaId(int deliveryAreaId) {
		this.deliveryAreaId = deliveryAreaId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getDriverId() {
		return driverId;
	}

	public void setDriverId(int driverId) {
		this.driverId = driverId;
	}

	public boolean verifyAreaName(String name) {
		if (name == null || name.trim().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean verifyRegionName(String name) {
		if (name == null || name.trim().isEmpty()) {
			return false;
		}
		return true;
	}
	
	public boolean verifyDriverId(int id) {
		if(id <= 0) {
			return false;
		}
		DeliveryPersonDAO driverDAO = new DeliveryPersonDAO();
		return driverDAO.findDeliveryPersonById(id) != null;
	}
	
	public boolean createDeliveryArea(String aname, String rname, int id) {
		return verifyAreaName(aname) && verifyRegionName(rname) && verifyDriverId(id);
	}
	
	public DeliveryArea() {
		
	}
	
}
