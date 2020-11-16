/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.KF5008.AssessmentA;

/**
 *
 * @author sheldonmlee
 */

/**
 * represents Axis Aligned Bounding Box.
 * Consists of two coordinates:
 * 		(x1, y1)+ + + +
 * 		+             +
 * 		+             +
 * 		+ + + +(x1, y2)
 */

class AABB
{
	public int x1, y1, x2, y2;

	public AABB(int x1,int y1,int x2,int y2) 
	{
		this.x1 = x1; this.y1 = y1; this.x2 = x2; this.y2 = y2;
	}
	
	private static boolean rangeIntersects(int l1, int u1, int l2, int u2)
	{
		return (l1 <= l2 && l2 <= u1 ||
				l1 <= u2 && u2 <= u1);
	}

	private static boolean inRange(int x, int lower, int upper)
	{
		return lower <= x && x <= upper;
	}

	public boolean phoneInside(AnyMobilePhone phone)
	{
		int x = phone.getHoz();
		int y = phone.getVert();

		return inRange(x, x1, x2) && inRange(y, y1, y2);
	}

	public boolean intersects(AABB other)
	{
		return 
			rangeIntersects(this.x1, this.x2, other.x1, other.x2) &&
			rangeIntersects(this.y1, this.y2, other.y1, other.y2);
	}

	public static void main(String args[])
	{
	}
}
