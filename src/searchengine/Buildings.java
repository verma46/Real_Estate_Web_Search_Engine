package searchengine;

public class Buildings  implements Comparable<Buildings>{

	public String address;
	public double price;
	public int bedrooms;
	public int washrooms;
	public String type;

	public Buildings(String address, double price, int bedrooms, int washrooms, String type) {
		super();
		this.address = address;
		this.price = price;
		this.bedrooms = bedrooms;
		this.washrooms = washrooms;
		this.type = type;
	}

	
	
	@Override
	public int compareTo(Buildings buildings) {
		if(this.getPrice() > buildings.getPrice()) {
            return 1;
        } else if (this.getPrice() < buildings.getPrice()) {
            return -1;
        } else {
            return 0;
        }
	}

	public double getPrice() {
        return price;
    }

	@Override
	public String toString() {
		return "Building [address=" + address + ", price=" + price + ", bedrooms=" + bedrooms +", washrooms="+washrooms+", type="+type+"]";
	}
	
}