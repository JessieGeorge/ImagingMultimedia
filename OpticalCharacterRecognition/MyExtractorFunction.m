function [Features, Labels] = MyExtractorFunction(im, label, locations, classes)

th = 200;
im2 = im;
im2(im >= th) = 0;
im2(im < th) = 1;
im2 = medfilt2(im2); %removing salt and pepper noise
L = bwlabel(im2);
[Features, Labels] = BoundingBox(L, im2, label, locations, classes);
title('Connected Components');
%{
Features = normc(Features);
D = dist2(Features, Features);
figure
imagesc(D)
title('Distance Matrix')
 %}   
end