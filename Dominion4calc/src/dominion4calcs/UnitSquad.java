package dominion4calcs;

import java.util.LinkedList;
import java.util.List;

public class UnitSquad {

	public Unit unit;
	private int x;
	private int y;

	public UnitSquad(Unit unit, int count, int x, int y) {
		this.unit = unit;
		for(int i=0; i < count; ++i) {
			states.add(new UnitState(unit));
		}
		this.x=x;
		this.y=y;
	}

	List<UnitState> states = new LinkedList<UnitState>();

	public boolean isRouted() {
		throw new UnsupportedOperationException("not implemented");
	}
	
}
