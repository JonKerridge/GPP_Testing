/**
 * defines scripts used to test the components.
 * They use the classes defined in TestDataDefs.
 * The script BuildAllTests can be used to convert the *.gpp versions into runnable
 * *.groovy versions.<p>
 * The script RunAllTests will then run all the tests not as Junit tests but just
 * as a script.  This does not run the Visualised versions as these must be run individually.
 *
 * The Tests are the same as appear in GPP_Library except that they can be built
 * using GPP_Builder and Visualised versions can also be created.
 *
 * <b>The scripts test the components.</b>
 *
 * 1.	Emit -> Worker(null()) -> Collect
 * 2.	Emit -> CombineNto1 -> Worker(null()) -> Collect
 * 3.	Emit -> CombineNto1 -> EmitFromInput -> Worker(null()) -> Collect
 * 4.	Emit -> ThreePhaseWorker(null()) -> Collect
 * 5.	EmitWithLocal -> Worker(null()) -> Collect
 * 6.	Emit -> OneFanAny -> AnyGroupAny(3, null()) -> AnyFanOne -> Collect
 * 7.	Emit -> OneFanAny -> AnyGroupAny(3, null()) -> AnyFanAny -> AnyGroupAny(3, null()) ->AnyFanOne -> Collect
 * 8.	Emit -> OneFanList -> ListGroupList(3, null()) -> ListFanOne -> Collect
 * 9.	Emit -> OneFanList -> ListGroupList(3, null()) -> ListMergeOne -> Collect
 * 10.	Emit -> OneSeqCastList -> ListGroupList(3, null()) -> ListFanOne -> Collect
 * 11.	Emit -> OneSeqCastList -> ListGroupList(3, null()) -> ListMergeOne -> Collect
 * 12.	Emit -> OneSeqCastList -> ListGroupList(3, null()) -> ListSeqOne -> Collect
 * 13.	Emit -> OneSeqCastList -> ListGroupList(3, null()) -> ListParOne -> Collect
 * 14.	Emit -> OneParCastList -> ListGroupList(3, null()) -> ListFanOne -> Collect
 * 15.	Emit -> OneParCastList -> ListGroupList(3, null()) -> ListMergeOne -> Collect
 * 16.	Emit -> OneParCastList -> ListGroupList(3, null()) -> ListSeqOne -> Collect
 * 17.	Emit -> OneParCastList -> ListGroupList(3, null()) -> ListParOne -> Collect
 * 18.	Emit -> OneParCastList -> ListGroupList(3, null()) -> N_WayMerge -> Collect
 * 19.	Emit -> OneFanAny -> AnyGroupList(3, f1(m1)) -> ListGroupList(3, f2(m2)) -> ListGroupAny(3, f3(m3)) -> AnyFanOne -> Collect
 * 20.	Emit -> OneFanList -> ListGroupList(3, f1(m1)) -> ListGroupList(3, f2(m2)) -> ListGroupAny(3, f3(m3)) -> AnyFanOne -> Collect
 * 21.	Emit -> OneFanList -> ListGroupList(3, f1(m1)) -> ListGroupList(3, f2(m2), sync) -> ListGroupAny(3, f3(m3)) -> AnyFanOne -> Collect
 * 22.	Emit -> OneFanList -> ListGroupList(3, f1(m1)) -> ListGroupList(3, f2(m2)) -> ListGroupList(3, f3(m3), sync) -> ListFanOne -> Collect
 * 23.	Emit -> OneFanList -> ListGroupList(3, f1(m1),sync) -> ListGroupList(3, f2(m2)) -> ListGroupList(3, f3(m3)) -> ListFanOne -> Collect
 * 24.	Emit -> OneFanList -> ListGroupList(3, f1(m1),sync) -> ListGroupList(3, f2(m2), sync) -> ListGroupList(3, f3(m3), sync) -> ListFanOne -> Collect
 * 25.	Emit -> OneFanAny -> AnyGroupAny(3, f1(m1)) -> AnyGroupAny(3, f2(m2))  -> AnyGroupAny(3, f3(m3)) -> AnyFanOne -> Collect
 * 26.	Emit -> OnePipelineOne -> Collect
 * 27.	Emit -> OnePipelineCollect
 * 28.	Emit -> OneFanAny -> GroupOfPipelineCollects
 * 29.	Emit -> OneFanList -> GroupOfPipelines -> ListFanOne -> Collect
 * 30.	Emit -> OneFanList -> GroupOfPipelines -> ListMergeOne -> Collect
 * 31.	Emit -> OneFanAny -> PipelineOfGroups->  AnyFanOne -> Collect
 *
 * <p>
  *
  */

package GPP_Testing;