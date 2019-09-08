package io.github.pps5.kakaosampleapp.feature.detail

const val TEMPLATE = """
<!DOCTYPE html>
<html>
  <head>
    <meta name="viewport"  content="width=device-width,initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/normalize/8.0.1/normalize.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css">
    <style>
      h1 { font-size: 1.7rem; }
      h2 { font-size: 1.5rem; }
      h3 { font-size: 1.35rem; }
      h4 { font-size: 1.20rem; }
      h5 { font-size: 1.1rem; }
      img { max-width: 100%%; }
      pre { overflow-x: auto; }
      body { overflow-wrap: break-word; }
    </style>
  </head>
  <body>
    <div class="container">
      <h1>%s</h1>
      <div style="display: inline">%s</div>
    </div>
    <div style="height: %spx;"></div>
  </body>
</html>
"""