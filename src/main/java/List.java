public class List {
    private String[] list = new String[100];
    private static int numOfItems;

    public List() {
        numOfItems = 0;
    }

    public void addItem(String s) {
        list[numOfItems] = s;
        numOfItems++;
    }

    public void removeItem() {
        numOfItems--;
        list[numOfItems] = "";
    }

    public String getItem(int i) {
        return list[i];
    }

    public int getNumOfItem() {
        return numOfItems;
    }
}
