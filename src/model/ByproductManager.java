package model;

import java.util.ArrayList;
import java.util.List;

public class ByproductManager {
    private List<Byproduct> byproducts;//içinde byProduct nesnelerini barındıran list

    public ByproductManager() {
        this.byproducts = new ArrayList<>();
    }

    public void addByproduct(Byproduct byproduct) {
        // Aynı isimde varsa miktarını artır
        for (Byproduct b : byproducts) {
            if (b.getName().equals(byproduct.getName())) {
                b.setAmount(b.getAmount() + byproduct.getAmount());
                return;
            }
        }
        byproducts.add(byproduct);
    }

    public void removeByproduct(String name, int amount) {
        byproducts.removeIf(b -> b.getName().equals(name) && b.getAmount() <= amount);
        for (Byproduct b : byproducts) {
            if (b.getName().equals(name) && b.getAmount() > amount) {
                b.setAmount(b.getAmount() - amount);
                break;
            }
        }
    }

    public List<Byproduct> getByproducts() { //bu metod yan ürünlerin kopyasını verir.
        return new ArrayList<>(byproducts);
    }
} 