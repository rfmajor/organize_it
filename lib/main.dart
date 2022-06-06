import 'package:flutter/material.dart';
import 'package:organize_it/addNote.dart';
import 'package:organize_it/editNote.dart';
import 'package:flutter/services.dart';
import 'package:organize_it/globals.dart' as globals;
import 'dart:async';

import 'addTask.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {

  const MyApp({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        // This is the theme of your application.
        //
        // Try running your application with "flutter run". You'll see the
        // application has a blue toolbar. Then, without quitting the app, try
        // changing the primarySwatch below to Colors.green and then invoke
        // "hot reload" (press "r" in the console where you ran "flutter run",
        // or simply save your changes to "hot reload" in a Flutter IDE).
        // Notice that the counter didn't reset back to zero; the application
        // is not restarted.
        primarySwatch: Colors.blue,
      ),
      debugShowCheckedModeBanner: false,
      home: const MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  const MyHomePage({Key? key, required this.title}) : super(key: key);

  // This widget is the home page of your application. It is stateful, meaning
  // that it has a State object (defined below) that contains fields that affect
  // how it looks.

  // This class is the configuration for the state. It holds the values (in this
  // case the title) provided by the parent (in this case the App widget) and
  // used by the build method of the State. Fields in a Widget subclass are
  // always marked "final".

  final String title;

  @override
  State<MyHomePage> createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      // This call to setState tells the Flutter framework that something has
      // changed in this State, which causes it to rerun the build method below
      // so that the display can reflect the updated values. If we changed
      // _counter without calling setState(), then the build method would not be
      // called again, and so nothing would appear to happen.
      _counter++;
    });
  }

  void updateView() {
    setState(() {

    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Organize_it!'),),
      floatingActionButton: Stack(
        fit: StackFit.expand,
        children: [
          Positioned(
              left: 40,
              bottom: 20,
              child: FloatingActionButton(
                heroTag: "add_task",
                child: Icon(Icons.add_task),
                onPressed: () {
                  Navigator.push(context, MaterialPageRoute(builder: (_)=>AddTask()));
                },
              )),
          Positioned(
              right: 40,
              bottom: 20,
              child: FloatingActionButton(
                heroTag: "add_note",
                child: Icon(Icons.note_add),
                onPressed: () {
                  Navigator.push(context, MaterialPageRoute(builder: (_)=>AddNote()));
                },
              ))
        ],
      ),
      body: GridView.builder(gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(crossAxisCount: 2),

          itemCount: 10, // nr of displayed notes
          itemBuilder: (_,index){
        return GestureDetector(
          onTap: () {
            Navigator.push(context, MaterialPageRoute(builder: (_)=>EditNote(/*note[index]*/)));
          },
          child:Container(
            margin: EdgeInsets.all(10),
            height: 150,
            color: Colors.grey[200],
            child: ListTile(
              title: Text("Title"),
            )
          ),);
      }),
    );
  }
}