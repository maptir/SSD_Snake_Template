package main;

import lib.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SnakeGame extends AbstractGame {

	private Random random = new Random();
	private Snake snake = new Snake(10, 10);
	private Map map = new Map();
	private Block apple = new Block(random.nextInt(map.getSize()), random.nextInt(map.getSize()));

	public SnakeGame() {
		for (Block block : snake.getSnake())
			map.addBlock(block);

		map.addBlock(apple);
	}

	public int getMapSize() {
		return map.getSize();
	}

	public List<Block> getBlocks() {
		return map.getBlocks();
	}

	public void loadGame(Memento m) {
		this.apple = m.apple;
		this.snake.getSnake().clear();
		this.snake.getSnake().addAll(m.body);
		this.snake.setDx(m.dx);
		this.snake.setDy(m.dy);
		this.map.getBlocks().clear();
		this.map.getBlocks().addAll(snake.getSnake());
		this.map.addBlock(m.apple);
	}

	public Memento saveGame() {
		return new Memento(apple, snake.getSnake(), snake.getDx(), snake.getDy());
	}

	static class Memento {

		private List<Block> body;
		private int dx, dy;
		private Block apple;

		public Memento(Block apple, List<Block> snakeBody, int dx, int dy) {
			this.dx = dx;
			this.dy = dy;
			this.apple = new Block(apple.getX(), apple.getY());
			this.body = new ArrayList<>();
			for (Block b : snakeBody)
				body.add(new Block(b.getX(), b.getY()));
		}
	}

	@Override
	protected void gameLogic() {
		snake.move();
		for (Block b : snake.getSnake()) {
			if (b.overlapped(apple)) {
				map.addBlock(snake.expand());
				apple.setX(random.nextInt(map.getSize()));
				apple.setY(random.nextInt(map.getSize()));
				break;
			}
		}
		if (snake.hitItself())
			end();
	}

	@Override
	protected void handleLeftKey() {
		snake.setDx(-1);
		snake.setDy(0);
	}

	@Override
	protected void handleRightKey() {
		snake.setDx(1);
		snake.setDy(0);
	}

	@Override
	protected void handleUpKey() {
		snake.setDx(0);
		snake.setDy(-1);
	}

	@Override
	protected void handleDownKey() {
		snake.setDx(0);
		snake.setDy(1);
	}

}
