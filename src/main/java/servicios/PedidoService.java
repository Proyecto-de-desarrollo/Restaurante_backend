package servicios;

import modelos.Pedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repositorio.PedidoRepository;

import java.util.List;

@Service
public class PedidoService {
    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoService(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    public Pedido save(Pedido pedido) {
        return pedidoRepository.save(pedido);
    }

    public Pedido findById(String id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> findAll() {
        return pedidoRepository.findAll();
    }

    public Pedido update(Pedido pedido) {
        return pedidoRepository.update(pedido);
    }

    public void deleteById(String id) {
        pedidoRepository.deleteById(id);
    }

    public List<Pedido> findByClienteId(String clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public List<Pedido> findByMesaId(String mesaId) {
        return pedidoRepository.findByMesaId(mesaId);
    }
}


