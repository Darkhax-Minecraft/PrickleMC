package net.darkhax.pricklemc.forge;

import net.darkhax.pricklemc.common.impl.Constants;
import net.darkhax.pricklemc.common.impl.PrickleMod;
import net.minecraftforge.fml.common.Mod;

@Mod(Constants.MOD_ID)
public class ForgeMod {

    public ForgeMod() {
        PrickleMod.getInstance().init();
    }
}