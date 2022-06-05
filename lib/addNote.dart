import 'package:flutter/material.dart';

class AddNote extends StatelessWidget {

  //bind with controlers
  TextEditingController title = TextEditingController();
  TextEditingController content = TextEditingController();





  @override
  Widget build(BuildContext) {
    return Scaffold(
      appBar: AppBar(
        actions: [
          FlatButton(onPressed: (){
            // on pressed

            // .whenComplete(()=>Navigator.pop(context)); // gp back to menu upon saving


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
            Expanded(child:
              Container(
                decoration: BoxDecoration(border: Border.all()),
                child: TextField(
                  controller: content,
                  maxLines: null,
                  expands: true,
                  decoration: InputDecoration(hintText: 'Content'),
                ),
              ),
            ),
          ],
        ),
      ),
    );
  }
}