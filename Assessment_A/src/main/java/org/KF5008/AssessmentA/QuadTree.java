/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.KF5008.AssessmentA;

import java.util.*;
import java.awt.Graphics2D;

/**
 *
 * @author sheldonmlee
 */

/**
 * Node node with four children
 */
class Node 
{
	private ArrayList<AnyMobilePhone> phones;
	private AABB aabb;
	int max;

	private Node nw = null;
	private Node ne = null;
	private Node sw = null;
	private Node se = null;

	private void subdivide()
	{
		int half_x, half_y;
		half_x = (aabb.x1 + aabb.x2)/2 ;
		half_y = (aabb.y1 + aabb.y2)/2 ;

		nw = new Node(new AABB(aabb.x1, aabb.y1, half_x-1, half_y-1), max);
		ne = new Node(new AABB(half_x, aabb.y1, aabb.x2, half_y-1), max);
		sw = new Node(new AABB(aabb.x1, half_y, half_x-1, aabb.y2), max);
		se = new Node(new AABB(half_x, half_y, aabb.x2, aabb.y2), max);

	}

	public Node(AABB aabb, int max)
	{
		this.phones = new ArrayList<>();
		this.aabb = aabb;
		this.max = max; 
	}

	// insert
	public void insert(AnyMobilePhone phone)
	{
		if (phones.size() + 1 > max) {
			subdivide();

			// put phones into correct children
			for (int i = 0; i < phones.size(); i++) {
			}
			
			// free as contents will be copied down to children
			phones = null;
		}
		else {
			phones.add(phone);
		}
	}
	
	public Node[] getChildren()
	{
		return new Node[] {nw, ne, sw, se};
	}

	public void draw(Graphics2D g) 
	{
		int x1, y1, x2, y2;
		x1 = aabb.x1; y1 = aabb.y1; x2 = aabb.x2; y2 = aabb.y2;

		g.drawLine(x1, y1, x2, y1);
		g.drawLine(x2, y1, x2, y2);
		g.drawLine(x2, y2, x1, y2);
		g.drawLine(x1, y2, x1, y1);

		for (Node node : getChildren()) {
			if (node == null) break;
			node.draw(g);
		}
	}

}

public class QuadTree
{
	Node root = null;
	AABB aabb;
	int max;

	QuadTree(AABB aabb, int max) 
	{
		this.aabb = aabb;
		this.max = max;
		this.root = new Node(aabb, max);
		System.out.println("QuadTree constructor called.");
	}

	void insert(AnyMobilePhone point)
	{
		root.insert(point);
	}

	//destroys and rebuilds quadtree from array of phones.
	public void construct(AnyMobilePhone[] phones)
	{
		root = null;
		root = new Node(aabb, max);
		for ( AnyMobilePhone phone: phones) {
			insert(phone);
		}
	}

	public void draw(Graphics2D g)
	{
		if (g == null) return;
		if (root == null) return;
		root.draw(g);
	}

	public static void main(String args[])
	{
		QuadTree quadtree = new QuadTree(new AABB(0, 0, 0, 0), 1);
		Node node = new Node(new AABB(0, 0, 0, 0), 1);
	}

}
