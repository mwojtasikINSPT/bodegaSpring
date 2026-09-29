package prog2.bodega_frontend.views;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import java.util.List;
import prog2.bodega_frontend.model.Licor;
import prog2.bodega_frontend.service.LicorService;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.confirmdialog.ConfirmDialog;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;

@Route("licores")
public class LicorListView extends VerticalLayout {

    private final LicorService licorService;
    private final Grid<Licor> grid = new Grid<>(Licor.class, false);
    private final ComboBox<String> filtroTipo = new ComboBox<>("Filtrar por tipo");

    public LicorListView(LicorService licorService) {

        this.licorService = licorService;

        H1 titulo = new H1("Licores");

        // Obtengo licores desde el backend.
        List<Licor> licores = licorService.buscarLicores();

        // Creo el botón para agregar un licor
        Button nuevoLicor = new Button("Nuevo licor");
        // Abro form para crear licor
        nuevoLicor.addClickListener(event -> {
            getUI().ifPresent(ui -> ui.navigate("licor-form"));
        });

        // Obtengo tipos existentes y elimino repetidos
        List<String> tipos = licores.stream()
                .map(Licor::getTipo)
                .filter(tipo -> tipo != null && !tipo.isBlank())
                .distinct()
                .sorted()
                .toList();
        // Cargo tipos como opciones del filtro
        filtroTipo.setItems(tipos);

        // Escucho cuando cambio el tipo
        filtroTipo.addValueChangeListener(event -> {
            String tipo = event.getValue();

            if (tipo == null) {
                // Muestro todos 
                grid.setItems(licores);
            } else {
                // Busco los del tipo elegido
                List<Licor> resultados = licorService.buscarLicoresPorTipo(tipo);
                grid.setItems(resultados);
            }
        });

        // Defino columnas que quiero mostrar.
        grid.addColumn(Licor::getId).setHeader("ID");
        grid.addColumn(Licor::getTipo).setHeader("Tipo");
        grid.addColumn(Licor::getMarca).setHeader("Marca");
        grid.addComponentColumn(licor -> {
            // Construyo la URL de la imagen que está en el backend.
            String url = "http://localhost:8080/images/" + licor.getFoto();

            // Creo componente de imagen usando  URL
            Image imagen = new Image(url, licor.getMarca());

            // Defino el tamaño de la imagen sin deformarla
            imagen.setWidth("80px");
            imagen.setHeight("80px");
            imagen.getStyle().set("object-fit", "contain");

            return imagen;
        }).setHeader("Foto");

        // Creo la columna de acciones
        grid.addComponentColumn(licor -> {
            //-----Editar--------
            Button editar = new Button("Editar");
            // Abro el formulario del licor seleccionado
            editar.addClickListener(event -> {
                getUI().ifPresent(ui -> ui.navigate("licor-form/" + licor.getId()));
            });

            //-----Eliminar--------
            Button eliminar = new Button("Eliminar");
            // Pregunto antes de eliminar
            eliminar.addClickListener(event -> {

                ConfirmDialog dialogo = new ConfirmDialog();
                dialogo.setHeader("Confirmar eliminación");
                dialogo.setText("¿Está seguro de eliminar " + licor.getMarca() + "?");

                dialogo.setCancelable(true);
                dialogo.setCancelText("Cancelar");

                dialogo.setConfirmText("Eliminar");

                // Elimino el licor cuando confirmo
                dialogo.addConfirmListener(confirmEvent -> {
                    licorService.eliminarLicor(licor.getId());
                    // Actualizo la lista después de eliminar
                    actualizarLista();
                    Notification.show("Licor eliminado correctamente");
                });

                dialogo.open();
            });

            return new HorizontalLayout(editar, eliminar);

        }).setHeader("Acciones");

        // Cargo licores obtenidos 
        grid.setItems(licores);

        add(titulo, filtroTipo, nuevoLicor, grid);
    }

    private void actualizarLista() {

        // Busco los licores
        List<Licor> licores = licorService.buscarLicores();

        // Actualizo la tabla
        grid.setItems(licores);

        // Actualizo los tipos del filtro
        List<String> tipos = licores.stream()
                .map(Licor::getTipo)
                .filter(tipo -> tipo != null && !tipo.isBlank())
                .distinct()
                .sorted()
                .toList();

        filtroTipo.setItems(tipos);
    }

}
