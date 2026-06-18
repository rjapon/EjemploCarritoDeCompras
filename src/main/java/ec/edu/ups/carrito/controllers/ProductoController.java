/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.carrito.controllers;

import ec.edu.ups.carrito.dao.ProductoDAO;
import ec.edu.ups.carrito.models.Producto;
import ec.edu.ups.carrito.views.CrearProductoView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Asus
 */
public class ProductoController {

    private ProductoDAO productoDAO;
    private CrearProductoView crearProductoView;

    public ProductoController(CrearProductoView crearProductoView, ProductoDAO productoDAO) {
        this.crearProductoView = crearProductoView;
        this.productoDAO = productoDAO;
        configurarEventosCrearProducto();
    }

    public void crearProducto() {

        int codigo = Integer.parseInt(crearProductoView.getTxtCodigo().getText());
        String nombre = crearProductoView.getTxtNombre().getText();
        double precio = Double.parseDouble(crearProductoView.getTxtPrecio().getText());

        Producto producto = new Producto(codigo, nombre, precio);
        productoDAO.crear(producto);

        crearProductoView.mostrarInformacion("Producto creado exitosamente");
        System.out.println("Producto creado exitosamente");

    }

    public void buscarProducto() {

        int codigo = Integer.parseInt(crearProductoView.getTxtCodigo().getText());
        if(productoDAO.buscar(codigo) != null){
            txt
        }

    }

    public void configurarEventosCrearProducto() {
        crearProductoView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearProducto();
            }
        });
    }

}
