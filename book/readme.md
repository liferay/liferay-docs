# Liferay Documentation Books Readme

The documentation in this branch has been concatenated together in all its sections and converted to LaTeX to create a PDF for publishing on [Liferay Learn](https://learn.liferay.com). It represents "legacy" documentation now. 

Follow these steps to build these PDFs: 

1. Make sure you have a LaTeX distribution installed. For Liferay, this was done with `lualatex` as shipped with Texlive. 

2. Copy all the images for all the sections of docs into the images folder here. There will be duplicates; overwrite them. 

3. Configure your `~/latexmkrc` file like this: 

   ```bash

    # PDF-generating modes are:
    # 1: pdflatex, as specified by $pdflatex variable (still largely in use)
    # 2: postscript conversion, as specified by the $ps2pdf variable (useless)
    # 3: dvi conversion, as specified by the $dvipdf variable (useless)
    # 4: lualatex, as specified by the $lualatex variable (best)
    # 5: xelatex, as specified by the $xelatex variable (second best)
    $pdf_mode = 4;
    $dvi_mode = '0';
    $pdf_previewer = 'start okular'

   ```
  
   Substitute your PDF viewer of choice if you don't use Okular. 

4. Run `latexmk` for each of the files: 

   ```bash

   latexmk developing-liferay-dxp-72.tex 

   latexmk using-liferay-dxp-72.tex
   
   ```

When `latexmk` finishes running, you should have a PDF. If the table of contents is blank, run `latexmk` on that file again. 
