package GUI;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

import Runner.TDMain;



public class TowerPurchasePanel extends JPanel
{
	Game game;
	private static String[] towerNames = {"Chogath", "Ezreal"};
	private static int[] towerCosts 	= {100		, 1000};
	private static Color[] towerColor	= {Color.cyan, Color.red};
	ArrayList<PurchaseBlock> purchaseBlocks = new ArrayList<PurchaseBlock>();
	
	public int getCost(String towerName)
	{
		for(int i = 0; i < towerNames.length; i++)
			if(towerNames[i].equals(towerName))
				return towerCosts[i];
		return -1;
	}
	
	public static Color getColor(String tower)
	{
		for(int i = 0; i < towerNames.length; i++)
			if(towerNames[i].equals(tower))
				return towerColor[i];
		return Color.yellow;
	}
	
	public TowerPurchasePanel(int w, int h, Game g)
	{
		setSize(w, h);
		game = g;
		
		for(int i = 0; i < towerNames.length; i++)
		{
			purchaseBlocks.add(new PurchaseBlock(towerNames[i], towerCosts[i], new Rectangle(75, i*100, 100, 75)));
		}
	}
	
	public void paint(Graphics g)
	{
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, this.getY(), getWidth(), getHeight());
		
		g.setColor(Color.white);
		for(PurchaseBlock b: purchaseBlocks)
		{
			g.drawString(b.type, b.buyButton.x-70, b.buyButton.y+30);
			g.drawString(b.cost+"", b.buyButton.x-70, b.buyButton.y+50);
			g.fillRect(b.buyButton.x, b.buyButton.y, b.buyButton.width, b.buyButton.height);
		}
	}
	
	
	
	class PurchaseBlock
	{
		String type;
		int cost;
		Rectangle buyButton;
		
		public PurchaseBlock(String type, int cost, Rectangle but)
		{
			this.type = type;
			this.cost = cost;
			buyButton = but;
		}
	}


	public String mouseClicked(MouseEvent arg0) {
		Point clickedPoint = new Point(arg0.getPoint().x - TDMain.FRAMEWIDTH ,arg0.getPoint().y-TDMain.OFFSET);
		if(arg0.getButton() == MouseEvent.BUTTON1)
		{
			for(PurchaseBlock b: purchaseBlocks)
			{
				if(b.buyButton.contains(clickedPoint))
				{
					if(game.getGold() >= b.cost)
						return b.type;
					else
						return "Not enough gold";
				}
			}
		}
		return null;
	}
}
