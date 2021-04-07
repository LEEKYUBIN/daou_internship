package crawlApp;

import java.util.HashSet;
import java.util.Set;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import common.Constant;
import controller.Controller;
import service.ItemService;

/*
 * 
 * 2021년 3월 25일 / 이규빈
 * 
 * main에서 Controller 를 호출하면
 * 
 *             -----> Service (ItemService) ----
 * Controller |                                 | ---> Repository
 *             -----> Service (InfoService) ---- 
 * 
 * 위와 같은 순서로 데이터 처리가 이루어지도록 작성하였습니다. 
 * 
 * 
 * 
 */
public class App {
	public static void main(String[] args) throws Exception {

		Set<String> nameSet = new HashSet<>();
		Controller controller = Controller.getInstance();
		ItemService itemService = ItemService.getInstance();
		for (int i = 1; i < Constant.maxPage; i++) {

			Document doc = Jsoup.connect(Constant.docFront + i + Constant.docBack).get();
			Elements shopList = doc.select(Constant.shopListString);
			for (Element shop : shopList) {

				String name = itemService.getCrawl(shop, 0, Constant.nameString).text();

				if (!nameSet.contains(name)) {
					nameSet.add(name);

					controller.save(name, shop, i);

				}
			}
		}
		System.out.println("종료");
	}
}
