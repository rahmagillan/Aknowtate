import io
def detect_text(path,infile):
    """Detects text in the file."""
    from google.cloud import vision
    client = vision.ImageAnnotatorClient()
    infile = open(infile, "w")
    with io.open(path, 'rb') as image_file:
        content = image_file.read()
    image = vision.types.Image(content=content)
    response = client.text_detection(image=image)
    texts = response.text_annotations
    infile.write(texts[0].description)
    infile.close()
   # for text in texts:
    #   print('\n"{}"'.format(text.description))
       # vertices = (['({},{})'.format(vertex.x, vertex.y)
         #           for vertex in text.bounding_poly.vertices])
        #print('bounds: {}'.format(','.join(vertices)))
detect_text("48268900_522740218222204_6177889063169687552_n.jpg","export.txt")
