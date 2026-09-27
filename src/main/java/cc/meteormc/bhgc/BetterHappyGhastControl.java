/*
 * This file is part of the TemplateMod project, licensed under the
 * GNU Lesser General Public License v3.0
 *
 * Copyright (C) 2023  Fallen_Breath and contributors
 *
 * TemplateMod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * TemplateMod is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with TemplateMod.  If not, see <https://www.gnu.org/licenses/>.
 */

package cc.meteormc.bhgc;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
//#if MC >= 1.18.2
//$$ import com.mojang.logging.LogUtils;
//$$ import org.slf4j.Logger;
//#else
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
//#endif

@net.minecraftforge.fml.common.Mod(BetterHappyGhastControl.MOD_ID)
@net.neoforged.fml.common.Mod(BetterHappyGhastControl.MOD_ID)
public class BetterHappyGhastControl
{
	public static final Logger LOGGER =
			//#if MC >= 11802
			//$$ LogUtils.getLogger();
			//#else
			LogManager.getLogger();
			//#endif

	public static final String MOD_ID = "bhgc";

	public static KeyMapping KEY_CONTROL_DOWN;

	public static void fabricInit()
	{
		//noinspection InstantiationOfUtilityClass
		new BetterHappyGhastControl();
	}

	public BetterHappyGhastControl()
	{
		// common init here
		KEY_CONTROL_DOWN = new KeyMapping(
				"Happy Ghast Down",
				//#if MC >= 260300
				//$$ InputConstants.Type.KEYBOARD,
				//#else
				InputConstants.Type.KEYSYM,
				//#endif
				InputConstants.KEY_LALT,
				KeyMapping.Category.MOVEMENT,
				Integer.MAX_VALUE
		);
	}
}
