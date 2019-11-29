package sample;

import BackEnd_Classes.*;
import javafx.scene.image.ImageView;
import java.util.*;


// TODO : Make class for sun and sun token

public class back_end_controler {
	private Map<ImageView, Plant> plantsInGarden = new HashMap<ImageView,Plant>() ;
//	public back_end_controler() {
//		plantsInGarden = new HashMap<ImageView,Plant>() ;
//		System.out.println("constructor of back end controller");
//	}
	public void addPlant(ImageView plantImgView,String ID) {
		Plant plant ;
		switch(ID) {
			case "beetroot" : plant = new Beetroot() ; break ;
			case "peashooter" : plant = new PeaShooter() ; break ;
			case "sunflower" : plant = new Sunflower() ; break ;
			case "walnut" : plant = new Walnut() ; break ;
			default: plant = null ;
		}
		plantsInGarden.put(plantImgView,plant) ;
	}
	public Plant getPlant(ImageView plant) {
		return plantsInGarden.get(plant) ;
	}
	public void fun(ImageView p ) {
		System.out.println(p.getId()  );
	}

}




