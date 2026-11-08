package newsagents;

public class DeliveryArea {
	
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
		DeliveryDriverDAO driverDAO = new DeliveryDriverDAO();
		return driverDAO.findCustomerById(id) != null && id > 0;
	}
	
	public boolean createDeliveryArea(String aname, String rname, int id) {
		return verifyAreaName(aname) && verifyRegionName(rname) && verifyDriverId(id);
	}
	
	public DeliveryArea() {
		
	}
	
}
