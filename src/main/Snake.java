package main;

import java.util.ArrayList;
import java.util.List;

import lib.Block;

public class Snake {

	private List<Block> body = new ArrayList<>();
	private int dx = 0, dy = -1;

	public Snake(int x, int y) {
		body.add(new Block(x, y));
		body.add(new Block(x, y + 1));
		body.add(new Block(x, y + 2));
	}

	public List<Block> getSnake() {
		return body;
	}

	public void move() {
		Block firstBlock = body.get(0);
		Block lastBlock = body.remove(body.size() - 1);
		lastBlock.setX(firstBlock.getX() + dx);
		lastBlock.setY(firstBlock.getY() + dy);
		body.add(0, lastBlock);
	}

	public Block expand() {
		Block lastBlock = body.get(body.size() - 1);
		Block last2Block = body.get(body.size() - 2);

		int ddx = lastBlock.getX() - last2Block.getX();
		int ddy = lastBlock.getY() - last2Block.getY();

		Block newBlock = new Block(lastBlock.getX() + ddx, lastBlock.getY() + ddy);
		body.add(newBlock);
		return newBlock;
	}

	public boolean hitItself() {
		Block head = body.get(0);

		for (int i = 1; i < body.size(); i++)
			if (head.overlapped(body.get(i)))
				return true;
		return false;
	}

	public void setDx(int dx) {
		this.dx = dx;
	}

	public void setDy(int dy) {
		this.dy = dy;
	}

	public int getDx() {
		return dx;
	}

	public int getDy() {
		return dy;
	}
}
