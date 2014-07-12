/*
 * This file is part of EchoPet.
 *
 * EchoPet is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * EchoPet is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with EchoPet.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.dsh105.echopet.nms.v1_7_R3.entity.type;

import com.dsh105.echopet.api.entity.PetType;
import com.dsh105.echopet.api.entity.SizeCategory;
import com.dsh105.echopet.api.entity.nms.type.EntityBlazePet;
import com.dsh105.echopet.api.entity.pet.Pet;
import com.dsh105.echopet.nms.v1_7_R3.entity.EntityPetImpl;
import com.dsh105.echopet.util.protocol.wrapper.WrapperPacketWorldParticles;
import net.minecraft.server.v1_7_R3.World;

@EntitySize(width = 0.6F, height = 1.7F)
@EntityPetData(petType = PetType.BLAZE)
public class EntityBlazePetImpl extends EntityPetImpl implements EntityBlazePet {

    public EntityBlazePetImpl(World world) {
        super(world);
    }

    public EntityBlazePetImpl(World world, Pet pet) {
        super(world, pet);
    }

    @Override
    public void setOnFire(boolean flag) {
        this.datawatcher.watch(16, (byte) (flag ? 1 : 0));
    }

    @Override
    protected void initDatawatcher() {
        super.initDatawatcher();
        this.datawatcher.a(16, new Byte((byte) 0));
    }

    @Override
    protected String getIdleSound() {
        return "mob.blaze.breathe";
    }

    @Override
    protected String getDeathSound() {
        return "mob.blaze.death";
    }

    @Override
    public SizeCategory getSizeCategory() {
        return SizeCategory.REGULAR;
    }

    @Override
    public void onLive() {
        super.onLive();
        if (this.random.nextBoolean() && particle <= 0 && !this.isInvisible()) {
            ParticleUtil.show(WrapperPacketWorldParticles.ParticleType.FIRE, this.getLocation());
            ParticleUtil.show(WrapperPacketWorldParticles.ParticleType.SMOKE, this.getLocation());
        }
    }
}