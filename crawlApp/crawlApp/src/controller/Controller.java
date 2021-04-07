package controller;

import org.jsoup.nodes.Element;

import service.InformationService;
import service.ItemService;
import vo.Item;

public class Controller {

	private static Controller uniqueInstance = new Controller();

	private Controller() {
	}

	public static Controller getInstance() {
		return uniqueInstance;
	}

	private ItemService itemService = ItemService.getInstance();
	private InformationService informationService = InformationService.getInstance();

	public void save(String name, Element shop, int i) {
		Item item = itemService.itemSave(name, shop, i);
		informationService.informationSave(shop, item.getNo(), name);
	}

}
