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
	private ArrayList<MobilePhone> phones;
	private AABB aabb;
	int max;
	int depth;

	private Node nw = null;
	private Node ne = null;
	private Node sw = null;
	private Node se = null;

	private void subdivide()
	{
		int x1, y1, x2, y2;
		x1 = aabb.x1;
		y1 = aabb.y1;
		x2 = aabb.x2;
		y2 = aabb.y2;

		if (x1 == x2 || y1 == y2) return;

		int half_x, half_y;
		half_x = (x1 + x2)/2 ;
		half_y = (y1 + y2)/2 ;

		nw = new Node(new AABB(x1, y1, half_x, half_y), max, depth+1);
		ne = new Node(new AABB(half_x, y1, x2, half_y), max, depth+1);
		sw = new Node(new AABB(x1, half_y, half_x, y2), max, depth+1);
		se = new Node(new AABB(half_x, half_y, x2, y2), max, depth+1);

		phones.forEach((phone) -> {
			insertIntoChildren(phone);
		});
		phones = null;
	}

	// put phone into correct children
	private void insertIntoChildren(MobilePhone phone) {
		Node nodes[] = getChildren();
		if (nodes == null) return;
		for (Node node : nodes) {
			if (node.aabb.phoneInside(phone)) node.insert(phone);
		}
	}

	public Node(AABB aabb, int max, int depth)
	{
		this.phones = new ArrayList<>();
		this.aabb = aabb;
		this.max = max; 
		this.depth = depth;
	}

	// insert into self if not subdivided and subdivide if it is maxed, 
	// otherwise insert into correct children.
	public void insert(MobilePhone phone)
	{
		if (nw == null) {
			if ((phones.size() + 1) > max) subdivide();
			else if(aabb.phoneInside(phone)) phones.add(phone);
		}
		else {
			insertIntoChildren(phone);
		}
	}

	public void query(AABB queryAABB, ArrayList<MobilePhone> returnArray)
	{
		if (nw == null && phones != null) {
			for (MobilePhone phone : phones){
				returnArray.add(phone);
			}
		}
		else {
			Node nodes[] = getChildren(); if (nodes == null) return;
			for (Node node : nodes) {
				if (queryAABB.intersects(node.aabb)) node.query(aabb, returnArray);
			}
		}
	}


	public Node[] getChildren()
	{
		if (nw == null) return null;
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

		Node children[] = getChildren();
		if (children == null) return;
		for (Node node : children) {
			node.draw(g);
		}
	}
}

public class QuadTree
{
	private Node root = null;
	private AABB aabb;
	private int max;

	public QuadTree(AABB aabb, int max) 
	{
		this.aabb = aabb;
		this.max = max;
		this.root = new Node(aabb, max, 0);
		System.out.println("QuadTree constructor called.");
	}

	public void insert(MobilePhone point)
	{
		root.insert(point);
	}

	public ArrayList<MobilePhone> query(AABB queryAABB) 
	{
		ArrayList<MobilePhone> returnArray = new ArrayList<>();
		root.query(queryAABB, returnArray);
		return returnArray;
	}

	public void resize(AABB aabb)
	{
		this.aabb = aabb;
		this.root = new Node(aabb, max, 0);
		System.out.printf("Resize Called: %d,%d,%d,%d\n", aabb.x1,aabb.y1,aabb.x2,aabb.y2);
	}

	//destroys and rebuilds quadtree from array of phones.
	public void construct(List<MobilePhone> phones)
	{
		root = null;
		root = new Node(aabb, max, 0);

		for (int i = 0; i < phones.size(); i++) {
			insert(phones.get(i));
		}
	}

	public void draw(Graphics2D g)
	{
		root.draw(g);
	}

	public static void main(String args[])
	{
		QuadTree quadtree = new QuadTree(new AABB(0, 0, 0, 0), 1);
	}

}
