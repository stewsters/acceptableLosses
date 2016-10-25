package acceptableLosses.components;

import acceptableLosses.assets.ItemType;
import com.artemis.PooledComponent;

public class Item extends PooledComponent {

    public ItemType itemType;

    @Override
    protected void reset() {
        itemType = null;
    }

    public Item set(ItemType itemType) {
        this.itemType = itemType;
        return this;
    }
}
