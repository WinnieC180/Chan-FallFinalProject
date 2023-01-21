public class Customer {
    private final String[] PIZZACRUST = {"thin", "thick", "normal"};
    private final String[] SAUCE = {"tomato", "none", "BBQ", "white"};
    private final String[] CHEESE = {"cheddar", "none", "Mozzarella", "provolone", "feta"};
    private final String[] TOPPINGS = {"olives", "pepperoni", "bacon", "onions", "bell peppers", "mushrooms"};
    private String order;
    private int x;
    private int y;
    private boolean delivered;

    public Customer() {
        int random = (int)(Math.random() * PIZZACRUST.length);
        order = "Crust: " + PIZZACRUST[random];

        random = (int)(Math.random() * SAUCE.length);
        order += "\nSauce: " + SAUCE[random];

        random = (int)(Math.random() * CHEESE.length);
        order += "\nCheese: " + CHEESE[random];

        int numOfToppings = (int)((Math.random() * 4));
        if (numOfToppings == 0) {
            order += "\nToppings: \n- none";
        }

        for (int i = 0; i < numOfToppings; i++) {
            random = (int)(Math.random() * TOPPINGS.length);
            if (!order.contains(TOPPINGS[random])) {
                order += "\n - " + TOPPINGS[random];
            }
        }

        x = (int)((Math.random() * 5));
        if (x == 0) {
            y = (int)((Math.random() * 4) + 1); //so this customer wont be at 0,0 which is where the user starts at
        } else {
            y = (int)((Math.random() * 5));
        }
        //max-min+1  + min

        delivered = false;
    }

    public void setDelivered () {
        delivered = true;
    }

    public boolean getDelivered() {
        return delivered;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getOrder() {
        return order;
    }
}