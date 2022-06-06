import 'package:flutter/material.dart';
import 'package:organize_it/globals.dart' as globals;

class AddTask extends StatelessWidget {

  TextEditingController title = TextEditingController();
  TextEditingController content = TextEditingController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        actions: [
          FlatButton(onPressed: (){

          }, child: Text('Save'))
        ],
      ),
      body: Container(
        margin: EdgeInsets.symmetric(horizontal: 20, vertical: 10),
        child: Column(
          children: [
            Container(
              decoration: BoxDecoration(border: Border.all()),
              child: TextField(
                controller: title,
                decoration: InputDecoration(hintText: 'Title'),
              ),
            ),
            SizedBox(height: 10,),
            Expanded(child: Container(
              decoration: BoxDecoration(border: Border.all()),
              child: TextField(
                controller: content,
                decoration: InputDecoration(hintText: 'Content'),
              ),
            ))
          ],
        ),
      ),
    );
  }
}