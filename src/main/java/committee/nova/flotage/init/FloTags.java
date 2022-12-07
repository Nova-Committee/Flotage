package committee.nova.flotage.init;

import committee.nova.flotage.Flotage;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import static net.minecraft.tags.ItemTags.create;

public class FloTags {
    public static final TagKey<Item> UNSTACKABLE = create(Flotage.asRes("unstackable"));

}
