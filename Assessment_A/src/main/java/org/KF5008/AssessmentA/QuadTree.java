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

	public ArrayList getAll() {
		ArrayList<MobilePhone> arr = new ArrayList<>();
		root.getAll(arr);
		return arr;
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

	private boolean canSubdivide() {
		return (aabb.x2-aabb.x1) > 2 && (aabb.y2-aabb.y1) > 2;
	}
	private void subdivide()
	{
		int x1, y1, x2, y2;
		x1 = aabb.x1;
		y1 = aabb.y1;
		x2 = aabb.x2;
		y2 = aabb.y2;

		int half_x, half_y;
		half_x = (x1 + x2)/2 ;
		half_y = (y1 + y2)/2 ;

		nw = new Node(new AABB(x1, y1, half_x, half_y), max, depth+1);
		ne = new Node(new AABB(half_x+1, y1, x2, half_y), max, depth+1);
		sw = new Node(new AABB(x1, half_y+1, half_x, y2), max, depth+1);
		se = new Node(new AABB(half_x+1, half_y+1, x2, y2), max, depth+1);

	}

	// put phone into correct children
	private void insertIntoChildren(MobilePhone phone) {
		Node nodes[] = getChildren(); if (nodes == null) return;
		for (Node node : nodes) {
			node.insert(phone);
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
		if (!aabb.phoneInside(phone)) return;
		if (nw != null) {
			insertIntoChildren(phone);
			return;
		}
		if (phones.size() == max && canSubdivide()) {
			subdivide();
			insertIntoChildren(phone);
			phones.forEach((n)->insertIntoChildren(n));
			phones = null;
		}
		else phones.add(phone);
	}

	public void query(AABB queryAABB, ArrayList<MobilePhone> returnArray)
	{
		if (!queryAABB.intersects(this.aabb)) return;
		if (nw == null) {
			for (MobilePhone phone : phones){
				returnArray.add(phone);
			}
		}
		else {
			Node nodes[] = getChildren();
			for (Node node : nodes) {
				node.query(queryAABB, returnArray);
			}
		}
	}

	public void getAll(ArrayList<MobilePhone> arr)
	{
		if (nw == null) {
			for (MobilePhone phone : phones) {
				arr.add(phone);
			}
			return;
		}
		Node nodes[] = getChildren();
		for (Node node : nodes) {
			node.getAll(arr);
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
