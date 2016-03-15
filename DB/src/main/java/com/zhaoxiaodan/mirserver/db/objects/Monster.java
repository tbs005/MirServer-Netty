package com.zhaoxiaodan.mirserver.db.objects;

import com.zhaoxiaodan.mirserver.db.entities.Player;
import com.zhaoxiaodan.mirserver.db.entities.StdMonster;
import com.zhaoxiaodan.mirserver.gameserver.engine.ItemEngine;
import com.zhaoxiaodan.mirserver.gameserver.engine.MonsterEngine;
import com.zhaoxiaodan.mirserver.gameserver.engine.ScriptEngine;
import com.zhaoxiaodan.mirserver.utils.NumUtil;

public class Monster extends AnimalObject {

	public final StdMonster stdMonster;

	public Player target;

	public Monster(StdMonster stdMonster) {
		this.stdMonster = stdMonster;
		this.hp = stdMonster.hp;
		this.maxHp = stdMonster.hp;
	}

	@Override
	public void beKilled(AnimalObject source) {
		super.beKilled(source);

		if (source instanceof Player)
			ItemEngine.createDropItems(MonsterEngine.getMonsterDrops(getName()), this.currMapPoint, (Player) source);
	}

	@Override
	public void damage(AnimalObject source, int power) {
		super.damage(source, power);

		if (source instanceof Player)
			ScriptEngine.exce(this.stdMonster.scriptName, "onDamage", this, (Player) source, power);
	}

	@Override
	public String getName() {
		return stdMonster.name;
	}

	@Override
	public boolean see(BaseObject object) {

		// 怪物只管看到的Player
		if (!(object instanceof Player))
			return false;

		return super.see(object);
	}

	@Override
	public int getFeature() {
		return NumUtil.makeLong(NumUtil.makeWord(stdMonster.raceImg, (byte) 0), stdMonster.appr);
	}

	@Override
	public short getFeatureEx() {
		return 0;
	}

	@Override
	public int getStatus() {
		return 0;
	}

	@Override
	public int getPower() {
		return NumUtil.randomRang(stdMonster.dc, stdMonster.dcMax);
	}

	@Override
	public int getDefend() {
		return stdMonster.ac;
	}

	@Override
	public void kill(AnimalObject animalObject) {

	}

	@Override
	public void onTick(long now) {
		if(this.objectsInView.size() > 0)
			ScriptEngine.exce(stdMonster.scriptName, "onTick", this, now);
	}
}
