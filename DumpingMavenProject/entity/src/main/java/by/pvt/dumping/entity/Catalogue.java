package by.pvt.dumping.entity;

import java.util.ArrayList;
import java.util.List;


public class Catalogue extends AbstractEntity {

	private List<Goods> catalogue = null;

	public Catalogue() {
		catalogue = new ArrayList<>();
	}

	public void addGoods(Goods goods) {
		catalogue.add(goods);
	}

	public List<Goods> getCatalogue() {
		return catalogue;
	}

	public void setCatalogue(List<Goods> catalogue) {
		this.catalogue = catalogue;
	}
	
}
