package prog2.bodega_frontend.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import prog2.bodega_frontend.model.Licor;
import prog2.bodega_frontend.service.LicorService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.html.H1;
import java.util.List;
import com.vaadin.flow.router.BeforeEvent;
import com.vaadin.flow.router.HasUrlParameter;
import com.vaadin.flow.router.OptionalParameter;

@Route("licor-form")
public class LicorFormView extends VerticalLayout implements HasUrlParameter<Integer> {

    private final LicorService licorService;
    private final ComboBox<String> tipo = new ComboBox<>("Tipo");
    private final TextField marca = new TextField("Marca");
    private final TextField foto = new TextField("Foto");
    private Integer id;
    private final H1 titulo = new H1("Nuevo licor");

    public LicorFormView(LicorService licorService) {

        this.licorService = licorService;

        // Creo el título del formulario
        add(titulo);

        // --------------Campos del formulario----------------
        List<Licor> licores = licorService.buscarLicores();

        // Obtengo y cargo tipos sin repetirlos
        List<String> tipos = licores.stream()
                .map(Licor::getTipo)
                .filter(valor -> valor != null && !valor.isBlank())
                .map(String::toLowerCase)
                .distinct()
                .sorted()
                .toList();
        tipo.setItems(tipos);

        // Permito escribir un tipo nuevo
        tipo.setAllowCustomValue(true);
        // Guardo el texto que escribo como valor del tipo
        tipo.addCustomValueSetListener(event -> tipo.setValue(event.getDetail()));

        // Creo el botón para guardar
        Button guardar = new Button("Guardar");

        // Creo el botón para cancelar
        Button cancelar = new Button("Cancelar");

        // Guardo el nuevo licor
        guardar.addClickListener(event -> {

            String tipoIngresado = tipo.getValue();

            // Normalizo el tipo antes de guardarlo
            if (tipoIngresado != null) {
                tipoIngresado = tipoIngresado.trim().toLowerCase();
            }

            if (tipoIngresado == null || tipoIngresado.isBlank() || marca.isEmpty()) {
                Notification.show("Tipo y marca son obligatorios");
                return;
            }

            Licor licor = new Licor(
                    null,
                    tipoIngresado,
                    marca.getValue(),
                    foto.getValue()
            );

            if (id == null) {
                // Guardo un licor nuevo
                licorService.guardarLicor(licor);
            } else {
                // Actualizo el licor existente
                licorService.actualizarLicor(id, licor);
            }

            Notification.show("Licor guardado correctamente");
            getUI().ifPresent(ui -> ui.navigate("licores"));
        });

        // Vuelvo a la lista sin guardar
        cancelar.addClickListener(event
                -> getUI().ifPresent(ui -> ui.navigate("licores"))
        );

        // Creo la fila de botones
        HorizontalLayout botones = new HorizontalLayout(guardar, cancelar);

        // Agrego los componentes al formulario
        add(tipo, marca, foto, botones);
    }

    // Implemento el método que recibe el ID de la URL
    @Override
    public void setParameter(BeforeEvent event, @OptionalParameter Integer id) {
        if (id != null) {
            this.id = id;
            // Cambio el título al editar
            titulo.setText("Editar licor");
            // Busco el licor que quiero editar
            Licor licor = licorService.buscarPorId(id);
            // Cargo los datos del licor
            tipo.setValue(licor.getTipo());
            marca.setValue(licor.getMarca());
            foto.setValue(licor.getFoto());
        }
    }
}
