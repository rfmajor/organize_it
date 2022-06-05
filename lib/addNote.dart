import 'package:flutter/material.dart';

class AddNote extends StatelessWidget {
  @override
  Widget build(BuildContext) {
    return Scaffold(
      appBar: AppBar(
        actions: [
          FlatButton(onPressed: (){}, child: Text('Save'))
        ],
      ),
      body: Column(
        children: [
          Container(
            decoration: BoxDecoration(border: Border.all()),
            child: TextField(
              decoration: InputDecoration(hintText: 'Title'),
            ),
          ),
          SizedBox(height: 10,),
          Expanded(child:
            Container(
              decoration: BoxDecoration(border: Border.all()),
              child: TextField(
                maxLines: null,
                expands: true,
                decoration: InputDecoration(hintText: 'Content'),
              ),
            ),
          ),
        ],
      ),
    );
  }
}