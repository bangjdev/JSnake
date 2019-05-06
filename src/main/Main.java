package main;

import java.awt.EventQueue;

import module.GUI;

public class Main {

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {

			@Override
			public void run() {
				new GUI();

			}
		});
	}
}
