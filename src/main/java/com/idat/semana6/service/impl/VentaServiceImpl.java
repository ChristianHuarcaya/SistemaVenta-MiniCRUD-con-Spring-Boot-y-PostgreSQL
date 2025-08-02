package com.idat.semana6.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.idat.semana6.entidad.DetalleVenta;
import com.idat.semana6.entidad.Venta;
import com.idat.semana6.repository.IProductoRepository;
import com.idat.semana6.repository.IVentaRepository;
import com.idat.semana6.service.IVentaService;

@Service
public class VentaServiceImpl implements IVentaService {
     @Autowired
	private IVentaRepository repository;
     
     @Autowired
     private IProductoRepository productoRepository;
	
	
     @Override
     public Venta registrar(Venta t) {
         
         BigDecimal importe = BigDecimal.ZERO;

         for (DetalleVenta det : t.getDetalleVenta()) {
             det.setVenta(t);

             det.setProducto(productoRepository.findById(det.getProducto().getId()).get());

             BigDecimal subtotal = det.getProducto().getPrecio()
                 .multiply(BigDecimal.valueOf(det.getCantidad()));

             det.setSubtotal(subtotal);

             importe = importe.add(subtotal); // sumamos el subtotal al importe total
         }

         t.setImportante(importe); // ✅ ahora es BigDecimal
         return repository.save(t);
     }


	@Override
	public Venta modificar(Venta t) {
		Optional<Venta> opt= repository.findById(t.getId());
		if(opt!=null) {
			return repository.save(t);
		}
		
		return null;
	}

	@Override
	public boolean eliminar(Long id) {
		Optional<Venta> opt= repository.findById(id);
		if(opt!=null) {
			repository.delete(opt.get());
			return true;
		}
		return false;
	}

	@Override
	public Venta buscar(Long id) {
		
		return repository.findById(id).get();
	}

	@Override
	public List<Venta> listar() {
		
		return repository.findAll();
	}

	@Override
	public Page<Venta> listarPagina(Pageable page) {
		
		return repository.findAll(page);
	}

}
