package com.example.lab_1_zakirov;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Lab_1_Zakirov extends Application {

    Pane root_pane = new Pane();        //группа для узлов сцены
    Group group = new Group();          //группа для узлов сцены
    Slider slider;
    Button btn_inc;
    Button btn_dec;
    RadioButton rb_1;
    RadioButton rb_2;
    RadioButton rb_3;
    CheckBox cb_1;
    CheckBox cb_2;
    CheckBox cb_3;
    TextArea textArea;
    DropShadow dropShadow;
    Text text;
    int counter = 0;
    final static int MAX_VAL = 50;


//=============================================================//
//                     Создание узлов (nodes)                  //
//=============================================================//
//=============================================================//
//                            Тень                             //
//=============================================================//

    private void Shadow() {

        dropShadow = new DropShadow();      //создать объект тень
        dropShadow.setRadius(5.0);          //закругление углов тени
        dropShadow.setOffsetX(5.0);         //смещение тени по Х
        dropShadow.setOffsetY(5.0);         //смещение тени по Y
        dropShadow.setColor(Color.GRAY);    //цвет тени
    }

//=============================================================//
//                           Графика                           //
//=============================================================//

    private void CreateGraphNodes() {
        //====== создать тень для узлов сцены
        Shadow();
        //====== применить эффект для всех узлов группы
        //для класса Pane тень не устанавливается, поэтому в Pane помещаем Group
        group.setEffect(dropShadow);
        //=== надпись
        text = new Text();                //создать текст
        text.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        text.setFill(Color.BLUE);         //цвет текста
    }

//=============================================================//
//                      Элементы управления                    //
//=============================================================//

    private void CreateControlNodes() {
        //====== RadioButton
        ToggleGroup tg = new ToggleGroup();     //группа для RadioButton
        rb_1 = new RadioButton("RadioButton 1");
        rb_1.setToggleGroup(tg);
        //
        rb_2 = new RadioButton("RadioButton 2");
        rb_2.setToggleGroup(tg);
        rb_2.setSelected(true);
        //
        rb_3 = new RadioButton("RadioButton 3");
        rb_3.setToggleGroup(tg);

        //====== CheckBox
        cb_1 = new CheckBox("CheckBox 1");
        cb_2 = new CheckBox("CheckBox 2");
        cb_3 = new CheckBox("CheckBox 3");

        //====== кнопка инкремент
        btn_inc = new Button();               //создать кнопку
        btn_inc.setText("Увеличить");         //надпись на кнопке
        btn_inc.setLayoutX(120);              //расположение кнопки на сцене по Х
        btn_inc.setLayoutY(200);              //расположение кнопки на сцене по У
        btn_inc.setTextFill(Color.BROWN);
        btn_inc.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        //====== кнопка декремент
        btn_dec = new Button();               //создать кнопку
        btn_dec.setText("Уменьшить");         //надпись на кнопке
        btn_dec.setLayoutX(20);               //расположение кнопки на сцене по Х
        btn_dec.setLayoutY(200);              //расположение кнопки на сцене по У
        btn_dec.setTextFill(Color.BROWN);
        btn_dec.setFont(Font.font("Arial", FontWeight.BOLD, 12));

        //====== дисплей
        textArea = new TextArea();
        textArea.setLayoutX(290);
        textArea.setLayoutY(20);
        textArea.setPrefSize(200, 150);
        textArea.setText("Лабораторная работа № 1 Закиров О.А");
        textArea.setWrapText(true);

        //====== Слайдер
        slider = new Slider();                 //создать слайдер
        slider.setLayoutX(290);                //координаты по Х
        slider.setLayoutY(200);                //координаты по У
        slider.setPrefWidth(200);              //длина слайдера
        slider.setBlockIncrement(1.0);         //единица изменения
        slider.setMajorTickUnit(10);           //большие деления на шкале
        slider.setMinorTickCount(5);           //малые деления на шкале
        slider.setMax(MAX_VAL);                //максимальное значение
        slider.setMin(1);                      //минимальное значение
        slider.setShowTickLabels(true);        //показать значения шкалы
        slider.setShowTickMarks(true);         //показать деления шкалы
        slider.setSnapToTicks(false);          //не привязывать к делениям

        //=== инициализация слайдера
        slider.setValue(counter);              //установить ползунок слайдера

        //====== скомпоновать сцену
        GridPane gp = new GridPane();               //создать компоновщик
        gp.setHgap(50);                             //шаг по горизонтали
        gp.setVgap(10);                             //шаг по верткали
        gp.setPadding(new Insets(25, 0, 0, 20));    //отступы от края окна

        //====== поместить объекты в компоновщик. (узел, столбец, строка)
        gp.add(rb_1, 0, 0);                         //RadioButton
        gp.add(rb_2, 0, 1);
        gp.add(rb_3, 0, 2);
        //
        gp.add(cb_1, 1, 0);                         //CheckBox
        gp.add(cb_2, 1, 1);
        gp.add(cb_3, 1, 2);
        //
        gp.add(text, 0, 8);                         //текст

        //====== поместить все узлы в корень
        group.getChildren().addAll(gp, slider, btn_dec, btn_inc, textArea);
        root_pane.getChildren().addAll(group);
        //====== отобразить установки параметров
        toDisplay(counter);
    }

//===============================================================
//                      Обработчики событий                    //
//===============================================================

    private void onAction() {

        /////////////////////////////////////////////////////////////
        //====== Button
        //=== обработчик события нажатия кнопки
        btn_inc.setOnAction((ActionEvent event) -> {
            if (counter < MAX_VAL) {
                counter += 1;                 //увеличить счетчик
            }
            toDisplay(counter);               //отобразить значение счетчика
        });

        //=== обработчик события нажатия кнопки
        btn_dec.setOnAction((ActionEvent event) -> {
            if (counter > 1) {
                counter -= 1;                 //уменьшить толщину линии
            }
            toDisplay(counter);           //отобразить значение счетчика
        });

        ////////////////////////////////////////////////////////////
        //====== Slider
        //=== обработчик события ползунка слайдера
        slider.valueProperty().addListener((observable) -> {
            if (slider.isValueChanging()) {
                counter = (int) slider.getValue(); //получить значение ползунка
                toDisplay(counter);                //отобразить значение счетчика                                                        }
            }
        });

        /////////////////////////////////////////////////////////////
        //====== CheckBox
        //=== обработчик события CheckBox_1
        cb_1.setOnAction((ActionEvent event) -> {
            if (cb_1.isSelected()) {
                textArea.appendText("\nУстановлен CheckBox 1");
            } else {
                textArea.appendText("\nСнят CheckBox 1");
            }
        });

        //=== обработчик события CheckBox_2
        cb_2.setOnAction((ActionEvent event) -> {
            if (cb_2.isSelected()) {
                textArea.appendText("\nУстановлен CheckBox 2");
            } else {
                textArea.appendText("\nСнят CheckBox 2");
            }
        });

        //=== обработчик события CheckBox_3
        cb_3.setOnAction((ActionEvent event) -> {
            if (cb_3.isSelected()) {
                textArea.appendText("\nУстановлен CheckBox 3");
            } else {
                textArea.appendText("\nСнят CheckBox 3");
            }
        });

        /////////////////////////////////////////////////////////////
        //====== RadioButton
        //=== обработчик события RadioButton_1
        rb_1.setOnAction((ActionEvent t) -> {
            textArea.appendText("\nУстановлен RadioButton 1");
        });

        //=== обработчик события RadioButton_2
        rb_2.setOnAction((ActionEvent t) -> {
            textArea.appendText("\nУстановлен RadioButton 2");
        });

        //=== обработчик события RadioButton_3
        rb_3.setOnAction((ActionEvent t) -> {
            textArea.appendText("\nУстановлен RadioButton 3");
        });
    }

//===============================================================
//                    Вывод значения счетчика                  //
//===============================================================

    public void toDisplay(double val) {
        String s = Integer.toString(counter);
        text.setText("Счетчик: " + s);            //значение счетчика
        textArea.appendText("\nСчетчик: " + s);   //значение счетчика
        slider.setValue(counter);                 //установить ползунок слайдера
    }

//===============================================================
//                             Начало                          //
//===============================================================

    @Override
    public void start(Stage stage) {
        //====== заголовок окна
        stage.setTitle("Лабораторная работа №1 Закиров О.А АИ-193");
        stage.setResizable(false);       //фиксированный размер
        //====== создать узлы сцены c графикой
        CreateGraphNodes();
        //====== создать узлы сцены c элементами управления
        CreateControlNodes();
        //====== создать обработчики событий
        onAction();
        //root_pane.setOpacity(0.50);
        //====== создать сцену с размерами и цветом фона
        Scene scene = new Scene(root_pane, 500, 250, Color.TRANSPARENT);
        //====== поместить сцену в окно
        stage.setScene(scene);
        //====== отобразить окно
        stage.show();
    }

//===============================================================
//  Точка входа в программу (запускает метод start)            //
//===============================================================

    public static void main(String[] args) {
        launch(args);
    }
}
