function result = RunMyOCRRecognition(filename, locations, classes)
featuresDB = [];
labelsDB = [];

I1 = imread('a.bmp');
label = 1;
prelim;

I1 = imread('d.bmp');
label = 2;
prelim;

I1 = imread('f.bmp');
label = 3;
prelim;

I1 = imread('h.bmp');
label = 4;
prelim;
    
I1 = imread('k.bmp');
label = 5;
prelim;

I1 = imread('m.bmp');
label = 6;
prelim;
    
I1 = imread('n.bmp');
label = 7;
prelim;
    
I1 = imread('o.bmp');
label = 8;
prelim;
    
I1 = imread('p.bmp');
label = 9;
prelim;
    
I1 = imread('q.bmp');
label = 10;
prelim;
    
I1 = imread('r.bmp');
label = 11;
prelim;
    
I1 = imread('s.bmp');
label = 12;
prelim;

I1 = imread('u.bmp');
label = 13;
prelim;
    
I1 = imread('w.bmp');
label = 14;
prelim;
    

I1 = imread('x.bmp');
label = 15;
prelim;
    
I1 = imread('z.bmp');
label = 16;
prelim;
    

TestImage = imread(filename);
[Features, Labels]= MyExtractorFunction(TestImage, label, locations, classes);

featuresDB = normc(featuresDB);
D = dist2(featuresDB, featuresDB);
figure
imagesc(D)
title('Distance Matrix')

%{
[D_sorted, D_index] = sort(D, 2);

figure
imagesc(conf)
title('Confusion Matrix') 
%}


end