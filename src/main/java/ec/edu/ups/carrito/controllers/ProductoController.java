/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.ups.carrito.controllers;

import ec.edu.ups.carrito.dao.ProductoDAO;
import ec.edu.ups.carrito.models.Producto;

import ec.edu.ups.carrito.views.CrearProductoView;
import ec.edu.ups.carrito.views.BuscarProductoView;
import ec.edu.ups.carrito.views.ActualizarProductoView;
import ec.edu.ups.carrito.views.EliminarProductoView;
import ec.edu.ups.carrito.views.ListarProductosView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.InternalFrameAdapter;
import javax.swing.event.InternalFrameEvent;

/**
 *
 * @author Asus
 */
public class ProductoController {

    private ProductoDAO productoDAO;
    private CrearProductoView crearProductoView;
    private BuscarProductoView buscarProductoView;
    private EliminarProductoView eliminarProductoView;
    private ActualizarProductoView actualizarProductoView;

    private ListarProductosView listarProductosView;

    public ProductoController(ListarProductosView listarProductosView, CrearProductoView crearProductoView, BuscarProductoView buscarProductoView, ActualizarProductoView actualizarProductoView, EliminarProductoView eliminarProductoView, ProductoDAO productoDAO) {
        this.buscarProductoView = buscarProductoView;
        this.crearProductoView = crearProductoView;
        this.actualizarProductoView = actualizarProductoView;
        this.eliminarProductoView = eliminarProductoView;

        this.listarProductosView = listarProductosView;

        this.productoDAO = productoDAO;

        configurarEventosCrearProducto();
        configurarEventosBuscarProducto();
        configurarEventosEliminarProducto();
        configurarEventosActualizarProducto();
        configurarEventosListarProductos();
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

    public void configurarEventosCrearProducto() {
        crearProductoView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearProducto();
            }
        });
    }

    public void buscarProducto() {

        Producto producto = productoDAO.buscar(Integer.parseInt(buscarProductoView.getTxtCodigoProducto().getText()));

        if (producto != null) {
            buscarProductoView.getTxtNombreProducto().setText(producto.getNombre());
            buscarProductoView.getTxtPrecioProducto().setText(String.valueOf(producto.getPrecio()));
        } else {
            buscarProductoView.mostrarInformacion("No se encontro el producto");
        }

    }

    private void configurarEventosBuscarProducto() {
        buscarProductoView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarProducto();
            }
        });

    }

    public void eliminarProducto() {

        Producto producto = productoDAO.buscar(Integer.parseInt(eliminarProductoView.getTxtCodigoProducto().getText()));

        if (producto != null) {
            int opcion = JOptionPane.showConfirmDialog(eliminarProductoView, "Deseas eliminar el producto?", "Seleccione", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                productoDAO.eliminar(producto.getCodigo());
                eliminarProductoView.mostrarInformacion("Producto eliminado");
            }
        } else {
            eliminarProductoView.mostrarInformacion("Producto no encontrado");
        }
    }

    private void buscarEliminarProducto() {
        Producto productoEncontrado = productoDAO.buscar(Integer.parseInt(eliminarProductoView.getTxtCodigoProducto().getText()));

        if (productoEncontrado != null) {
            eliminarProductoView.getTxtNombreProducto().setText(productoEncontrado.getNombre());
            eliminarProductoView.getTxtPrecioProducto().setText(String.valueOf(productoEncontrado.getPrecio()));

        } else {
            eliminarProductoView.mostrarInformacion("No se encontro el producto");
        }

    }

    private void configurarEventosEliminarProducto() {
        eliminarProductoView.getBtnEliminar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                eliminarProducto();
            }

        });

        eliminarProductoView.getBtnBuscarProducto().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEliminarProducto();
            }

        });
    }

    public void actualizarProducto() {

        int codigo = Integer.parseInt(actualizarProductoView.getTxtCodigoProducto().getText());
        Producto producto = productoDAO.buscar(Integer.parseInt(actualizarProductoView.getTxtCodigoProducto().getText()));

        if (producto != null) {

            String nombre = actualizarProductoView.getTxtActualizarNombre().getText();
            double precio = Double.parseDouble(actualizarProductoView.getTxtActualizarPrecio().getText());

            Producto productoActualizado = new Producto();
            productoActualizado.setCodigo(codigo);
            productoActualizado.setNombre(nombre);
            productoActualizado.setPrecio(precio);
            productoDAO.actualizar(codigo, productoActualizado);

            actualizarProductoView.mostrarInformacion("Producto actualizado");

        }

    }

    private void buscarActualizarProducto() {

        Producto productoEncontrado = productoDAO.buscar(Integer.parseInt(actualizarProductoView.getTxtCodigoProducto().getText()));

        if (productoEncontrado != null) {

            actualizarProductoView.getTxtActualizarNombre().setText(productoEncontrado.getNombre());
            actualizarProductoView.getTxtActualizarPrecio().setText(String.valueOf(productoEncontrado.getPrecio()));

        } else {
            actualizarProductoView.mostrarInformacion("No se encontro el producto");
        }

    }

    private void configurarEventosActualizarProducto() {
        actualizarProductoView.getBtnAceptar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarProducto();
            }

        });

        actualizarProductoView.getBtnBuscar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarActualizarProducto();
            }

        });
    }

    public void listarProductos() {

        List<Producto> lista = productoDAO.listar();
        listarProductosView.cargarDatos(lista);

    }

    // Lista
    public void configurarEventosListarProductos() {

        listarProductosView.addInternalFrameListener(new InternalFrameAdapter() {
            @Override
                    public void internalFrameActivated(InternalFrameEvent e) {
                listarProductos();
            }
        });

        listarProductosView.getBtnListar().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                listarProductos();
            }
        });

    }

}
