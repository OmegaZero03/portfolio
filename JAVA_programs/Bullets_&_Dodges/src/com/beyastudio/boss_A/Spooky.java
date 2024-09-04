package com.beyastudio.boss_A;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import com.beyastudio.entities.Entity;
import com.beyastudio.main.Main;
import com.beyastudio.wolrd.Camera;

public class Spooky extends Entity{
	
	int time = 0;
	int dir = 0;
	
	
	private double dx, dy, angle;
	private String estado = "parado";
	
	private boolean att = true, canCreat = true;
	
	//Controle das animações por switch_case
	/*
	 * idle
	 * blink
	 * attack
	 */
	
	private String animationState = "idle";
	
	public int damageFrames = 5, currentFrames = 0;
	
	private BufferedImage[] idleAnimationSprites, blinkAnimationSprites;
	
	private boolean canResetFrames = true;
	
	private int frames = 0,
			maxFrames = 20,
			index = 0,
			maxIndex = 3;
	
	private int idlespriteNum, blinkspriteNum;

	public Spooky(int x, int y, int width, int height, BufferedImage sprite) {
		super(x, y, width, height, sprite);
		
		this.life = 10000;
		this.maxLife = 10000;
		
		this.idlespriteNum = 4;
		this.blinkspriteNum = 4;
		
		
		idleAnimationSprites = new BufferedImage[idlespriteNum];
		blinkAnimationSprites = new BufferedImage[blinkspriteNum];

	}
	
	
	@Override
	public void tick() {
		
		
		if(att) {
			frames++;
			if(frames == maxFrames) {
				frames = 0;
				index++;
				if(index > maxIndex) {
					index = 0;
				}
			
			}
		}
		
		if(isDamaged) {
			this.currentFrames++;
			if(this.currentFrames == this.damageFrames) {
				this.currentFrames = 0;
				isDamaged = false;
			}
		}
		
		
		
		
		stateMachine();
	}
	
	
	@Override
	public void render(Graphics g) {
		
		//super.render(g);
		
		if(!isDamaged) {
			switch(this.animationState) {
			
				case "idle":
					
					if(canResetFrames) {
						index = 0;
						frames = 0;
						maxFrames = 20;
						canResetFrames = false;
					}
					
					for(int i=0; i < this.idlespriteNum; i++) {
					idleAnimationSprites[i] = Main.boss_samurai.getSpritesheet(0 + (16 * i),0, 16, 16);
					g.drawImage(idleAnimationSprites[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
				}
					
					break;
					
					
				case "blink":
					
					if(canResetFrames) {
						index = 0;
						frames = 0;
						maxFrames = 25;
						canResetFrames = false;
					}

					for(int i=0; i < this.blinkspriteNum; i++) {
					idleAnimationSprites[i] = Main.boss_samurai.getSpritesheet(0 + (i*16), 16, 16, 16);
					g.drawImage(idleAnimationSprites[index], this.getX() - Camera.x, this.getY() - Camera.y, null);
					
					if(index == 3) {
						maxFrames = 45;
					}else {
						maxFrames = 25;
					}
					
				}
					
					break;
					
					
				case "attack":
					
					break;
			}
			
		}else {
		
		}
		
		
		if (life != maxLife) {
			g.setColor(Color.red);
			g.fillRect((int) x - Camera.x - 3, (int) y - 4 - Camera.y, 20, 2);
			g.setColor(new Color(0xff5c31ab));
			g.fillRect((int) x - Camera.x - 3, (int) y - 4 - Camera.y, (int) ((life / maxLife) * 20), 2);
		}

	}
	
	
	public void setAnimation() {
		
		
	}
	
	
	
	public void stateMachine() {
		
		switch(estado) {
		
		case "parado":
			
			att = true;
		
			
			if(life != maxLife) {
			this.estado = "fase_1";
				canCreat = true;
				this.animationState = "blink";
			}
			
			break;
			
			
		case "fase_1":
			
			
			if(canCreat) {
				canCreat = false;
				this.canResetFrames = true;
			}
			
			if(life < 9950) {
				this.animationState = "idle";
			}
			
			
			
//         SE QUISER Q ELE ANDE EM UM LOSANGULO
//			time++;
//			
//			
//			float spd = .5f;
//			
//			switch(dir) {
//				case 0:
//					y+=spd;
//					x-=spd;
//					
//					if(time >= 100) {
//						dir = 1;
//					}
//					
//					break;
//					
//				case 1:
//					y+=spd;
//					x+=spd;
//					System.out.println("???");
//					if(time >= 200) {
//						dir = 2;
//					}
//					
//					break;
//					
//				case 2:
//					y-=spd;
//					x+=spd;
//					
//					if(time > 300) {
//						dir = 3;
//					}
//					
//					break;
//					
//				case 3:
//					y-=spd;
//					x-=spd;
//					
//					if(time > 400) {
//						time = 0;
//						dir = 0;
//					}
//					
//					break;
//				
//			}
//			
//			
//
//			
//			int playerY = Main.player.getY() - 5;
//			int playerX = Main.player.getX() - 3;
//			double dis = this.calculateDistance(this.getX(), this.getY(), Main.player.getX(), Main.player.getY());
//			
//			if(dis > 0) {
//				
////				walking = false;
////				attacking = true;
//				
//				spd = .5f;
//
//				angle = Math.atan2(playerY- y, playerX - x);
//
//				dx = Math.cos(angle);
//				dy = Math.sin(angle);
//			
//				x += dx * spd;
//				y += dy * spd;
//			}
//			
//			break;
		}
	}

}
